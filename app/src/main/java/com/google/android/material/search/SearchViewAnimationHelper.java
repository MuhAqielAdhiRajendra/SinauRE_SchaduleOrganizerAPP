package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.BackEventCompat;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationCoordinator;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.FadeThroughDrawable;
import com.google.android.material.internal.FadeThroughUpdateListener;
import com.google.android.material.internal.MultiViewUpdateListener;
import com.google.android.material.internal.RectEvaluator;
import com.google.android.material.internal.ReversableAnimatedValueInterpolator;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.internal.TouchObserverFrameLayout;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialMainContainerBackHelper;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.search.SearchView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes13.dex */
class SearchViewAnimationHelper {
    private static final float CONTENT_FROM_SCALE = 0.95f;
    private static final int DEFAULT_DURATION_MS = 100;
    private static final TimeInterpolator DEFAULT_INTERPOLATOR = AnimationUtils.LINEAR_INTERPOLATOR;
    private static final long HIDE_CLEAR_BUTTON_ALPHA_DURATION_MS = 42;
    private static final long HIDE_CLEAR_BUTTON_ALPHA_START_DELAY_MS = 0;
    private static final long HIDE_CONTENT_ALPHA_DURATION_MS = 83;
    private static final long HIDE_CONTENT_ALPHA_START_DELAY_MS = 0;
    private static final long HIDE_CONTENT_SCALE_DURATION_MS = 250;
    private static final long HIDE_DURATION_MS = 250;
    private static final long HIDE_TRANSLATE_DURATION_MS = 300;
    private static final long SHOW_CLEAR_BUTTON_ALPHA_DURATION_MS = 50;
    private static final long SHOW_CLEAR_BUTTON_ALPHA_START_DELAY_MS = 250;
    private static final long SHOW_CONTENT_ALPHA_DURATION_MS = 150;
    private static final long SHOW_CONTENT_ALPHA_START_DELAY_MS = 75;
    private static final long SHOW_CONTENT_SCALE_DURATION_MS = 300;
    private static final long SHOW_DURATION_MS = 300;
    private static final long SHOW_SCRIM_ALPHA_DURATION_MS = 100;
    private static final long SHOW_TRANSLATE_DURATION_MS = 350;
    private static final long SHOW_TRANSLATE_KEYBOARD_START_DELAY_MS = 150;
    AnimationCoordinator activeCoordinator;
    AnimatorSet activeTranslateAnimatorSet;
    final AnimationDelegate animationDelegate;
    private final MaterialMainContainerBackHelper backHelper;
    private AnimatorSet backProgressAnimatorSet;
    private final View backgroundView;
    private final ImageButton clearButton;
    private final TouchObserverFrameLayout contentContainer;
    private final Context context;
    private final View divider;
    private final TextView dummyTextView;
    private final Toolbar dummyToolbar;
    private final int durationShort1;
    private final int durationShort2;
    private final EditText editText;
    private final FrameLayout headerContainer;
    private final ClippableRoundedCornerLayout rootView;
    private final View scrim;
    private SearchBar searchBar;
    private final TextView searchPrefix;
    private final SearchView searchView;
    private final TimeInterpolator standardAccelerateInterpolator;
    private final TimeInterpolator standardDecelerateInterpolator;
    private final Toolbar toolbar;
    private final FrameLayout toolbarContainer;

    private interface AnimationDelegate {
        AnimatorSet getExpandCollapseAnimatorSet(boolean z);

        List<SpringAnimation> getExpandCollapseSpringAnimations(boolean z);

        void onAnimationEnd(boolean z);

        void onAnimationStart(boolean z);

        void setUpDummyToolbarIfNeeded();

        void startButtonsTranslationAnimation();
    }

    SearchViewAnimationHelper(Context context, SearchView searchView, boolean containedAnimationEnabled) {
        AnimationDelegate defaultAnimationDelegate;
        this.context = context;
        this.searchView = searchView;
        this.scrim = searchView.scrim;
        this.backgroundView = searchView.backgroundView;
        this.rootView = searchView.rootView;
        this.headerContainer = searchView.headerContainer;
        this.toolbarContainer = searchView.toolbarContainer;
        this.toolbar = searchView.toolbar;
        this.dummyToolbar = searchView.dummyToolbar;
        this.searchPrefix = searchView.searchPrefix;
        this.dummyTextView = searchView.dummyTextView;
        this.editText = searchView.editText;
        this.clearButton = searchView.clearButton;
        this.divider = searchView.divider;
        this.contentContainer = searchView.contentContainer;
        this.backHelper = new MaterialMainContainerBackHelper(this.rootView);
        this.standardAccelerateInterpolator = MotionUtils.resolveThemeInterpolator(context, R.attr.motionEasingStandardAccelerateInterpolator, DEFAULT_INTERPOLATOR);
        this.standardDecelerateInterpolator = MotionUtils.resolveThemeInterpolator(context, R.attr.motionEasingStandardDecelerateInterpolator, DEFAULT_INTERPOLATOR);
        this.durationShort1 = MotionUtils.resolveThemeDuration(context, R.attr.motionDurationShort1, 100);
        this.durationShort2 = MotionUtils.resolveThemeDuration(context, R.attr.motionDurationShort2, 100);
        if (containedAnimationEnabled) {
            defaultAnimationDelegate = new ContainedAnimationDelegate();
        } else {
            defaultAnimationDelegate = new DefaultAnimationDelegate();
        }
        this.animationDelegate = defaultAnimationDelegate;
    }

    void setSearchBar(SearchBar searchBar) {
        this.searchBar = searchBar;
    }

    void show() {
        cancelPendingAnimations();
        if (this.searchBar != null) {
            startShowAnimationExpand();
        } else {
            startShowAnimationTranslate();
        }
    }

    AnimatorSet hide() {
        cancelPendingAnimations();
        if (this.searchBar != null) {
            return startHideAnimationCollapse();
        }
        return startHideAnimationTranslate();
    }

    void cancelPendingAnimations() {
        if (this.activeCoordinator != null) {
            this.activeCoordinator.clear();
            this.activeCoordinator = null;
        }
        if (this.activeTranslateAnimatorSet != null) {
            this.activeTranslateAnimatorSet.cancel();
            this.activeTranslateAnimatorSet = null;
        }
    }

    private void startShowAnimationExpand() {
        if (this.searchView.isAdjustNothingSoftInputMode()) {
            this.searchView.requestFocusAndShowKeyboardIfNeeded();
        }
        this.searchView.setTransitionState(SearchView.TransitionState.SHOWING);
        this.animationDelegate.setUpDummyToolbarIfNeeded();
        this.editText.setText(this.searchBar.getText());
        this.editText.setSelection(this.editText.getText().length());
        this.rootView.setVisibility(4);
        this.rootView.post(new Runnable() { // from class: com.google.android.material.search.SearchViewAnimationHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m8865x94743afc();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$startShowAnimationExpand$0$com-google-android-material-search-SearchViewAnimationHelper, reason: not valid java name */
    /* synthetic */ void m8865x94743afc() {
        final boolean show = true;
        final AnimationCoordinator coordinator = new AnimationCoordinator();
        coordinator.addAnimator(getExpandCollapseAnimatorSet(true));
        for (SpringAnimation springAnimation : getExpandCollapseSpringAnimations(true)) {
            coordinator.addDynamicAnimation(springAnimation);
        }
        coordinator.addListener(new AnimationCoordinator.Listener() { // from class: com.google.android.material.search.SearchViewAnimationHelper.1
            @Override // com.google.android.material.animation.AnimationCoordinator.Listener
            public void onAnimationsStart() {
                SearchViewAnimationHelper.this.animationDelegate.onAnimationStart(show);
                SearchViewAnimationHelper.this.rootView.setVisibility(0);
                SearchViewAnimationHelper.this.searchBar.stopOnLoadAnimation();
            }

            @Override // com.google.android.material.animation.AnimationCoordinator.Listener
            public void onAnimationsEnd() {
                SearchViewAnimationHelper.this.animationDelegate.onAnimationEnd(show);
                if (!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                    SearchViewAnimationHelper.this.searchView.requestFocusAndShowKeyboardIfNeeded();
                }
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.SHOWN);
                if (SearchViewAnimationHelper.this.activeCoordinator == coordinator) {
                    SearchViewAnimationHelper.this.activeCoordinator = null;
                }
            }
        });
        coordinator.start();
        this.activeCoordinator = coordinator;
    }

    private AnimatorSet startHideAnimationCollapse() {
        if (this.searchView.isAdjustNothingSoftInputMode()) {
            this.editText.clearFocus();
        }
        final boolean show = false;
        final AnimationCoordinator coordinator = new AnimationCoordinator();
        AnimatorSet animatorSet = getExpandCollapseAnimatorSet(false);
        coordinator.addAnimator(animatorSet);
        for (SpringAnimation springAnimation : getExpandCollapseSpringAnimations(false)) {
            coordinator.addDynamicAnimation(springAnimation);
        }
        coordinator.addListener(new AnimationCoordinator.Listener() { // from class: com.google.android.material.search.SearchViewAnimationHelper.2
            @Override // com.google.android.material.animation.AnimationCoordinator.Listener
            public void onAnimationsStart() {
                SearchViewAnimationHelper.this.animationDelegate.onAnimationStart(show);
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.HIDING);
            }

            @Override // com.google.android.material.animation.AnimationCoordinator.Listener
            public void onAnimationsEnd() {
                SearchViewAnimationHelper.this.animationDelegate.onAnimationEnd(show);
                SearchViewAnimationHelper.this.rootView.setVisibility(8);
                if (!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                    SearchViewAnimationHelper.this.editText.clearFocus();
                }
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.HIDDEN);
                if (SearchViewAnimationHelper.this.activeCoordinator == coordinator) {
                    SearchViewAnimationHelper.this.activeCoordinator = null;
                }
            }
        });
        coordinator.start();
        this.activeCoordinator = coordinator;
        return animatorSet;
    }

    private void startShowAnimationTranslate() {
        if (this.searchView.isAdjustNothingSoftInputMode()) {
            SearchView searchView = this.searchView;
            final SearchView searchView2 = this.searchView;
            Objects.requireNonNull(searchView2);
            searchView.postDelayed(new Runnable() { // from class: com.google.android.material.search.SearchViewAnimationHelper$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    searchView2.requestFocusAndShowKeyboardIfNeeded();
                }
            }, 150L);
        }
        this.rootView.setVisibility(4);
        this.rootView.post(new Runnable() { // from class: com.google.android.material.search.SearchViewAnimationHelper$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m8866x4df249eb();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$startShowAnimationTranslate$1$com-google-android-material-search-SearchViewAnimationHelper, reason: not valid java name */
    /* synthetic */ void m8866x4df249eb() {
        this.rootView.setTranslationY(this.rootView.getHeight());
        final AnimatorSet animatorSet = getTranslateAnimatorSet(true);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.search.SearchViewAnimationHelper.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                SearchViewAnimationHelper.this.rootView.setVisibility(0);
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.SHOWING);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                if (!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                    SearchViewAnimationHelper.this.searchView.requestFocusAndShowKeyboardIfNeeded();
                }
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.SHOWN);
                if (SearchViewAnimationHelper.this.activeTranslateAnimatorSet == animatorSet) {
                    SearchViewAnimationHelper.this.activeTranslateAnimatorSet = null;
                }
            }
        });
        animatorSet.start();
        this.activeTranslateAnimatorSet = animatorSet;
    }

    private AnimatorSet startHideAnimationTranslate() {
        if (this.searchView.isAdjustNothingSoftInputMode()) {
            this.editText.clearFocus();
        }
        final AnimatorSet animatorSet = getTranslateAnimatorSet(false);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.search.SearchViewAnimationHelper.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.HIDING);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                SearchViewAnimationHelper.this.rootView.setVisibility(8);
                if (!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                    SearchViewAnimationHelper.this.editText.clearFocus();
                }
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.HIDDEN);
                if (SearchViewAnimationHelper.this.activeTranslateAnimatorSet == animatorSet) {
                    SearchViewAnimationHelper.this.activeTranslateAnimatorSet = null;
                }
            }
        });
        animatorSet.start();
        this.activeTranslateAnimatorSet = animatorSet;
        return animatorSet;
    }

    private AnimatorSet getTranslateAnimatorSet(boolean show) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(getTranslationYAnimator());
        addBackButtonProgressAnimatorIfNeeded(animatorSet);
        animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.of(show, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        animatorSet.setDuration(show ? SHOW_TRANSLATE_DURATION_MS : 300L);
        return animatorSet;
    }

    private Animator getTranslationYAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(this.rootView.getHeight(), 0.0f);
        animator.addUpdateListener(MultiViewUpdateListener.translationYListener(this.rootView));
        return animator;
    }

    private AnimatorSet getExpandCollapseAnimatorSet(boolean show) {
        AnimatorSet animatorSet = this.animationDelegate.getExpandCollapseAnimatorSet(show);
        if (this.backProgressAnimatorSet == null) {
            animatorSet.playTogether(getButtonsProgressAnimator(show));
        }
        return animatorSet;
    }

    private List<SpringAnimation> getExpandCollapseSpringAnimations(boolean show) {
        return this.animationDelegate.getExpandCollapseSpringAnimations(show);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animator getClearButtonAnimator(boolean show) {
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setDuration(show ? SHOW_CLEAR_BUTTON_ALPHA_DURATION_MS : HIDE_CLEAR_BUTTON_ALPHA_DURATION_MS);
        animator.setStartDelay(show ? 250L : 0L);
        animator.setInterpolator(ReversableAnimatedValueInterpolator.of(show, AnimationUtils.LINEAR_INTERPOLATOR));
        animator.addUpdateListener(MultiViewUpdateListener.alphaListener(this.clearButton));
        return animator;
    }

    private AnimatorSet getButtonsProgressAnimator(boolean show) {
        AnimatorSet animatorSet = new AnimatorSet();
        addBackButtonProgressAnimatorIfNeeded(animatorSet);
        animatorSet.setDuration(show ? 300L : 250L);
        animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.of(show, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet;
    }

    private void addBackButtonProgressAnimatorIfNeeded(AnimatorSet animatorSet) {
        ImageButton backButton = ToolbarUtils.getNavigationIconButton(this.toolbar);
        if (backButton == null) {
            return;
        }
        Drawable drawable = DrawableCompat.unwrap(backButton.getDrawable());
        if (this.searchView.isAnimatedNavigationIcon()) {
            addDrawerArrowDrawableAnimatorIfNeeded(animatorSet, drawable);
            addFadeThroughDrawableAnimatorIfNeeded(animatorSet, drawable);
            addBackButtonAnimatorIfNeeded(animatorSet, backButton);
            return;
        }
        setFullDrawableProgressIfNeeded(drawable);
    }

    private void addBackButtonAnimatorIfNeeded(AnimatorSet animatorSet, final ImageButton backButton) {
        if (this.searchBar == null || this.searchBar.getNavigationIcon() != null) {
            return;
        }
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.search.SearchViewAnimationHelper$$ExternalSyntheticLambda5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                backButton.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        animatorSet.playTogether(animator);
    }

    private void addDrawerArrowDrawableAnimatorIfNeeded(AnimatorSet animatorSet, Drawable drawable) {
        if (drawable instanceof DrawerArrowDrawable) {
            final DrawerArrowDrawable drawerArrowDrawable = (DrawerArrowDrawable) drawable;
            ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.search.SearchViewAnimationHelper$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    drawerArrowDrawable.setProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
            animatorSet.playTogether(animator);
        }
    }

    private void addFadeThroughDrawableAnimatorIfNeeded(AnimatorSet animatorSet, Drawable drawable) {
        if (drawable instanceof FadeThroughDrawable) {
            final FadeThroughDrawable fadeThroughDrawable = (FadeThroughDrawable) drawable;
            ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.search.SearchViewAnimationHelper$$ExternalSyntheticLambda2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    fadeThroughDrawable.setProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
            });
            animatorSet.playTogether(animator);
        }
    }

    private void setFullDrawableProgressIfNeeded(Drawable drawable) {
        if (drawable instanceof DrawerArrowDrawable) {
            ((DrawerArrowDrawable) drawable).setProgress(1.0f);
        }
        if (drawable instanceof FadeThroughDrawable) {
            ((FadeThroughDrawable) drawable).setProgress(1.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldInflateDummyToolbar() {
        return this.searchBar.getMenuResId() != -1 && this.searchView.isMenuItemsAnimated() && hasVisibleMenuItems(this.searchBar.getMenu());
    }

    private boolean hasVisibleMenuItems(Menu menu) {
        if (menu == null) {
            return false;
        }
        for (int i = 0; i < menu.size(); i++) {
            if (menu.getItem(i).isVisible()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMenuItemsNotClickable(Toolbar toolbar) {
        ActionMenuView actionMenuView = ToolbarUtils.getActionMenuView(toolbar);
        if (actionMenuView != null) {
            for (int i = 0; i < actionMenuView.getChildCount(); i++) {
                View menuItem = actionMenuView.getChildAt(i);
                menuItem.setClickable(false);
                menuItem.setFocusable(false);
                menuItem.setFocusableInTouchMode(false);
            }
        }
    }

    void startBackProgress(BackEventCompat backEvent) {
        this.backHelper.startBackProgress(backEvent, this.searchBar);
    }

    public void updateBackProgress(BackEventCompat backEvent) {
        if (backEvent.getProgress() <= 0.0f) {
            return;
        }
        this.backHelper.updateBackProgress(backEvent, this.searchBar, this.searchBar.getCornerSize());
        if (this.backProgressAnimatorSet == null) {
            if (this.searchView.isAdjustNothingSoftInputMode()) {
                this.editText.clearFocus();
            }
            if (!this.searchView.isAnimatedNavigationIcon()) {
                return;
            }
            this.backProgressAnimatorSet = getButtonsProgressAnimator(false);
            this.backProgressAnimatorSet.start();
            this.backProgressAnimatorSet.pause();
            return;
        }
        this.backProgressAnimatorSet.setCurrentPlayTime((long) (backEvent.getProgress() * this.backProgressAnimatorSet.getDuration()));
    }

    public BackEventCompat onHandleBackInvoked() {
        return this.backHelper.onHandleBackInvoked();
    }

    public void finishBackProgress() {
        AnimatorSet hideAnimatorSet = hide();
        long totalDuration = hideAnimatorSet.getTotalDuration();
        this.backHelper.finishBackProgress(totalDuration, this.searchBar);
        if (this.backProgressAnimatorSet != null) {
            this.animationDelegate.startButtonsTranslationAnimation();
            this.backProgressAnimatorSet.resume();
        }
        this.backProgressAnimatorSet = null;
    }

    public void cancelBackProgress() {
        this.backHelper.cancelBackProgress(this.searchBar);
        if (this.backProgressAnimatorSet != null) {
            this.backProgressAnimatorSet.reverse();
        }
        this.backProgressAnimatorSet = null;
    }

    MaterialMainContainerBackHelper getBackHelper() {
        return this.backHelper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBackgroundAlpha(float alpha) {
        this.backgroundView.getBackground().mutate().setAlpha((int) (255.0f * alpha));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContentViewsAlpha(float alpha) {
        this.clearButton.setAlpha(alpha);
        this.divider.setAlpha(alpha);
        this.contentContainer.setAlpha(alpha);
        setActionMenuViewAlphaIfNeeded(alpha);
    }

    private void setActionMenuViewAlphaIfNeeded(float alpha) {
        ActionMenuView actionMenuView;
        if (this.searchView.isMenuItemsAnimated() && (actionMenuView = ToolbarUtils.getActionMenuView(this.toolbar)) != null) {
            actionMenuView.setAlpha(alpha);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getTranslationXBetweenViews(View searchBarSubView, View searchViewSubView) {
        if (searchBarSubView == null) {
            int marginStart = ((ViewGroup.MarginLayoutParams) searchViewSubView.getLayoutParams()).getMarginStart();
            int paddingStart = this.searchBar.getPaddingStart();
            int searchBarLeft = getViewLeftFromSearchViewParent(this.searchBar);
            if (ViewUtils.isLayoutRtl(this.searchBar)) {
                return (((this.searchBar.getWidth() + searchBarLeft) + marginStart) - paddingStart) - this.searchView.getRight();
            }
            return (searchBarLeft - marginStart) + paddingStart;
        }
        return getViewLeftFromSearchViewParent(searchBarSubView) - getViewLeftFromSearchViewParent(searchViewSubView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getViewLeftFromSearchViewParent(View v) {
        int left = v.getLeft();
        for (ViewParent viewParent = v.getParent(); (viewParent instanceof View) && viewParent != this.searchView.getParent(); viewParent = viewParent.getParent()) {
            left += ((View) viewParent).getLeft();
        }
        return left;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getViewTopFromSearchViewParent(View v) {
        int top = v.getTop();
        for (ViewParent viewParent = v.getParent(); (viewParent instanceof View) && viewParent != this.searchView.getParent(); viewParent = viewParent.getParent()) {
            top += ((View) viewParent).getTop();
        }
        return top;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class DefaultAnimationDelegate implements AnimationDelegate {
        private DefaultAnimationDelegate() {
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void setUpDummyToolbarIfNeeded() {
            Menu menu = SearchViewAnimationHelper.this.dummyToolbar.getMenu();
            if (menu != null) {
                menu.clear();
            }
            boolean zShouldInflateDummyToolbar = SearchViewAnimationHelper.this.shouldInflateDummyToolbar();
            SearchViewAnimationHelper searchViewAnimationHelper = SearchViewAnimationHelper.this;
            if (zShouldInflateDummyToolbar) {
                searchViewAnimationHelper.dummyToolbar.inflateMenu(SearchViewAnimationHelper.this.searchBar.getMenuResId());
                SearchViewAnimationHelper.this.setMenuItemsNotClickable(SearchViewAnimationHelper.this.dummyToolbar);
                SearchViewAnimationHelper.this.dummyToolbar.setVisibility(0);
                return;
            }
            searchViewAnimationHelper.dummyToolbar.setVisibility(8);
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public AnimatorSet getExpandCollapseAnimatorSet(boolean show) {
            AnimatorSet animatorSet = new AnimatorSet();
            if (SearchViewAnimationHelper.this.backProgressAnimatorSet == null) {
                animatorSet.playTogether(getButtonsTranslationAnimator(show));
            }
            animatorSet.playTogether(getScrimAlphaAnimator(show), getRootViewAnimator(show), SearchViewAnimationHelper.this.getClearButtonAnimator(show), getContentAnimator(show), getHeaderContainerAnimator(show), getDummyToolbarAnimator(show), getActionMenuViewsAlphaAnimator(show), getEditTextAnimator(show), getSearchPrefixAnimator(show), getTextAnimator(show));
            return animatorSet;
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public List<SpringAnimation> getExpandCollapseSpringAnimations(boolean show) {
            return new ArrayList();
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void onAnimationStart(boolean show) {
            SearchViewAnimationHelper.this.setContentViewsAlpha(show ? 0.0f : 1.0f);
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void onAnimationEnd(boolean show) {
            SearchViewAnimationHelper.this.setContentViewsAlpha(show ? 1.0f : 0.0f);
            SearchViewAnimationHelper.this.editText.setAlpha(1.0f);
            if (SearchViewAnimationHelper.this.searchBar != null) {
                SearchViewAnimationHelper.this.searchBar.getTextView().setAlpha(1.0f);
            }
            SearchViewAnimationHelper.this.editText.setClipBounds(null);
            SearchViewAnimationHelper.this.rootView.resetClipBoundsAndCornerRadii();
            if (!show) {
                SearchViewAnimationHelper.this.backHelper.clearExpandedCornerRadii();
            }
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void startButtonsTranslationAnimation() {
            getButtonsTranslationAnimator(false).start();
        }

        private Animator getScrimAlphaAnimator(boolean show) {
            TimeInterpolator interpolator = show ? AnimationUtils.LINEAR_INTERPOLATOR : AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
            ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
            animator.setDuration(show ? 300L : 250L);
            animator.setStartDelay(show ? SearchViewAnimationHelper.SHOW_SCRIM_ALPHA_DURATION_MS : 0L);
            animator.setInterpolator(ReversableAnimatedValueInterpolator.of(show, interpolator));
            animator.addUpdateListener(MultiViewUpdateListener.alphaListener(SearchViewAnimationHelper.this.scrim));
            return animator;
        }

        private Animator getRootViewAnimator(boolean show) {
            Rect toClipBounds;
            Rect fromClipBounds;
            Rect initialHideToClipBounds = SearchViewAnimationHelper.this.backHelper.getInitialHideToClipBounds();
            Rect initialHideFromClipBounds = SearchViewAnimationHelper.this.backHelper.getInitialHideFromClipBounds();
            if (initialHideToClipBounds == null) {
                toClipBounds = ViewUtils.calculateRectFromBounds(SearchViewAnimationHelper.this.searchView);
            } else {
                toClipBounds = initialHideToClipBounds;
            }
            if (initialHideFromClipBounds == null) {
                fromClipBounds = ViewUtils.calculateOffsetRectFromBounds(SearchViewAnimationHelper.this.rootView, SearchViewAnimationHelper.this.searchBar);
            } else {
                fromClipBounds = initialHideFromClipBounds;
            }
            final Rect clipBounds = new Rect(fromClipBounds);
            final float fromCornerRadius = SearchViewAnimationHelper.this.searchBar.getCornerSize();
            final float[] toCornerRadius = maxCornerRadii(SearchViewAnimationHelper.this.rootView.getCornerRadii(), SearchViewAnimationHelper.this.backHelper.getExpandedCornerRadii());
            ValueAnimator animator = ValueAnimator.ofObject(new RectEvaluator(clipBounds), fromClipBounds, toClipBounds);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.search.SearchViewAnimationHelper$DefaultAnimationDelegate$$ExternalSyntheticLambda1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.m8873x8b5d297b(fromCornerRadius, toCornerRadius, clipBounds, valueAnimator);
                }
            });
            animator.setDuration(show ? 300L : 250L);
            animator.setInterpolator(ReversableAnimatedValueInterpolator.of(show, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            return animator;
        }

        /* JADX INFO: renamed from: lambda$getRootViewAnimator$0$com-google-android-material-search-SearchViewAnimationHelper$DefaultAnimationDelegate, reason: not valid java name */
        /* synthetic */ void m8873x8b5d297b(float fromCornerRadius, float[] toCornerRadius, Rect clipBounds, ValueAnimator valueAnimator) {
            float[] cornerRadii = lerpCornerRadii(fromCornerRadius, toCornerRadius, valueAnimator.getAnimatedFraction());
            SearchViewAnimationHelper.this.rootView.updateClipBoundsAndCornerRadii(clipBounds, cornerRadii);
        }

        private float[] maxCornerRadii(float[] startValue, float[] endValue) {
            return new float[]{Math.max(startValue[0], endValue[0]), Math.max(startValue[1], endValue[1]), Math.max(startValue[2], endValue[2]), Math.max(startValue[3], endValue[3]), Math.max(startValue[4], endValue[4]), Math.max(startValue[5], endValue[5]), Math.max(startValue[6], endValue[6]), Math.max(startValue[7], endValue[7])};
        }

        private float[] lerpCornerRadii(float startValue, float[] endValue, float fraction) {
            return new float[]{AnimationUtils.lerp(startValue, endValue[0], fraction), AnimationUtils.lerp(startValue, endValue[1], fraction), AnimationUtils.lerp(startValue, endValue[2], fraction), AnimationUtils.lerp(startValue, endValue[3], fraction), AnimationUtils.lerp(startValue, endValue[4], fraction), AnimationUtils.lerp(startValue, endValue[5], fraction), AnimationUtils.lerp(startValue, endValue[6], fraction), AnimationUtils.lerp(startValue, endValue[7], fraction)};
        }

        private Animator getDummyToolbarAnimator(boolean show) {
            return getTranslationAnimator(show, SearchViewAnimationHelper.this.dummyToolbar, getFromTranslationXEnd(SearchViewAnimationHelper.this.dummyToolbar) - (SearchViewAnimationHelper.this.searchBar.getPaddingEnd() - SearchViewAnimationHelper.this.dummyToolbar.getPaddingEnd()), getFromTranslationY());
        }

        private Animator getHeaderContainerAnimator(boolean show) {
            return getTranslationAnimator(show, SearchViewAnimationHelper.this.headerContainer, getFromTranslationXEnd(SearchViewAnimationHelper.this.headerContainer), getFromTranslationY());
        }

        private Animator getActionMenuViewsAlphaAnimator(boolean show) {
            ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
            animator.setDuration(show ? 300L : 250L);
            animator.setInterpolator(ReversableAnimatedValueInterpolator.of(show, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            if (SearchViewAnimationHelper.this.searchView.isMenuItemsAnimated()) {
                ActionMenuView dummyActionMenuView = ToolbarUtils.getActionMenuView(SearchViewAnimationHelper.this.dummyToolbar);
                ActionMenuView actionMenuView = ToolbarUtils.getActionMenuView(SearchViewAnimationHelper.this.toolbar);
                animator.addUpdateListener(new FadeThroughUpdateListener(dummyActionMenuView, actionMenuView));
            }
            return animator;
        }

        private Animator getSearchPrefixAnimator(boolean show) {
            return getTranslationAnimatorForText(show, SearchViewAnimationHelper.this.searchPrefix);
        }

        private Animator getEditTextAnimator(boolean show) {
            return getTranslationAnimatorForText(show, SearchViewAnimationHelper.this.editText);
        }

        private AnimatorSet getTextAnimator(boolean show) {
            AnimatorSet animatorSet = new AnimatorSet();
            addTextFadeAnimatorIfNeeded(animatorSet);
            addEditTextClipAnimator(animatorSet);
            animatorSet.setDuration(show ? 300L : 250L);
            animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.of(show, AnimationUtils.LINEAR_INTERPOLATOR));
            return animatorSet;
        }

        private void addEditTextClipAnimator(AnimatorSet animatorSet) {
            if (SearchViewAnimationHelper.this.searchBar == null || !TextUtils.equals(SearchViewAnimationHelper.this.editText.getText(), SearchViewAnimationHelper.this.searchBar.getText())) {
                return;
            }
            final Rect editTextClipBounds = new Rect(0, 0, SearchViewAnimationHelper.this.editText.getWidth(), SearchViewAnimationHelper.this.editText.getHeight());
            ValueAnimator animator = ValueAnimator.ofInt(SearchViewAnimationHelper.this.searchBar.getTextView().getWidth(), SearchViewAnimationHelper.this.editText.getWidth());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.search.SearchViewAnimationHelper$DefaultAnimationDelegate$$ExternalSyntheticLambda2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.m8871xb169caa7(editTextClipBounds, valueAnimator);
                }
            });
            animatorSet.playTogether(animator);
        }

        /* JADX INFO: renamed from: lambda$addEditTextClipAnimator$1$com-google-android-material-search-SearchViewAnimationHelper$DefaultAnimationDelegate, reason: not valid java name */
        /* synthetic */ void m8871xb169caa7(Rect editTextClipBounds, ValueAnimator animation) {
            editTextClipBounds.right = ((Integer) animation.getAnimatedValue()).intValue();
            SearchViewAnimationHelper.this.editText.setClipBounds(editTextClipBounds);
        }

        private void addTextFadeAnimatorIfNeeded(AnimatorSet animatorSet) {
            if (SearchViewAnimationHelper.this.searchBar == null || TextUtils.equals(SearchViewAnimationHelper.this.editText.getText(), SearchViewAnimationHelper.this.searchBar.getText())) {
                return;
            }
            ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.search.SearchViewAnimationHelper$DefaultAnimationDelegate$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.m8872x8539623c(valueAnimator);
                }
            });
            animatorSet.playTogether(animator);
        }

        /* JADX INFO: renamed from: lambda$addTextFadeAnimatorIfNeeded$2$com-google-android-material-search-SearchViewAnimationHelper$DefaultAnimationDelegate, reason: not valid java name */
        /* synthetic */ void m8872x8539623c(ValueAnimator animation) {
            SearchViewAnimationHelper.this.editText.setAlpha(((Float) animation.getAnimatedValue()).floatValue());
            SearchViewAnimationHelper.this.searchBar.getTextView().setAlpha(1.0f - ((Float) animation.getAnimatedValue()).floatValue());
        }

        private Animator getTranslationAnimatorForText(boolean show, View v) {
            TextView textView = SearchViewAnimationHelper.this.searchBar.getPlaceholderTextView();
            if (TextUtils.isEmpty(textView.getText()) || show) {
                textView = SearchViewAnimationHelper.this.searchBar.getTextView();
            }
            int startX = SearchViewAnimationHelper.this.getViewLeftFromSearchViewParent(textView) - SearchViewAnimationHelper.this.getViewLeftFromSearchViewParent(v);
            if (ViewUtils.isLayoutRtl(SearchViewAnimationHelper.this.searchBar)) {
                startX += textView.getWidth() - v.getWidth();
            }
            return getTranslationAnimator(show, v, startX, getFromTranslationY());
        }

        private Animator getContentAnimator(boolean show) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(getContentAlphaAnimator(show), getDividerAnimator(show), getContentScaleAnimator(show));
            return animatorSet;
        }

        private Animator getContentAlphaAnimator(boolean show) {
            ValueAnimator animatorAlpha = ValueAnimator.ofFloat(0.0f, 1.0f);
            animatorAlpha.setDuration(show ? 150L : SearchViewAnimationHelper.HIDE_CONTENT_ALPHA_DURATION_MS);
            animatorAlpha.setStartDelay(show ? 75L : 0L);
            animatorAlpha.setInterpolator(ReversableAnimatedValueInterpolator.of(show, AnimationUtils.LINEAR_INTERPOLATOR));
            animatorAlpha.addUpdateListener(MultiViewUpdateListener.alphaListener(SearchViewAnimationHelper.this.divider, SearchViewAnimationHelper.this.contentContainer));
            return animatorAlpha;
        }

        private Animator getDividerAnimator(boolean show) {
            float dividerTranslationY = (SearchViewAnimationHelper.this.contentContainer.getHeight() * 0.050000012f) / 2.0f;
            ValueAnimator animatorDivider = ValueAnimator.ofFloat(dividerTranslationY, 0.0f);
            animatorDivider.setDuration(show ? 300L : 250L);
            animatorDivider.setInterpolator(ReversableAnimatedValueInterpolator.of(show, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            animatorDivider.addUpdateListener(MultiViewUpdateListener.translationYListener(SearchViewAnimationHelper.this.divider));
            return animatorDivider;
        }

        private Animator getContentScaleAnimator(boolean show) {
            ValueAnimator animatorScale = ValueAnimator.ofFloat(SearchViewAnimationHelper.CONTENT_FROM_SCALE, 1.0f);
            animatorScale.setDuration(show ? 300L : 250L);
            animatorScale.setInterpolator(ReversableAnimatedValueInterpolator.of(show, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            animatorScale.addUpdateListener(MultiViewUpdateListener.scaleListener(SearchViewAnimationHelper.this.contentContainer));
            return animatorScale;
        }

        private Animator getTranslationAnimator(boolean show, View view, int startX, int startY) {
            ValueAnimator animatorX = ValueAnimator.ofFloat(startX, 0.0f);
            animatorX.addUpdateListener(MultiViewUpdateListener.translationXListener(view));
            ValueAnimator animatorY = ValueAnimator.ofFloat(startY, 0.0f);
            animatorY.addUpdateListener(MultiViewUpdateListener.translationYListener(view));
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(animatorX, animatorY);
            animatorSet.setDuration(show ? 300L : 250L);
            animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.of(show, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            return animatorSet;
        }

        private int getFromTranslationXEnd(View view) {
            int marginEnd = ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).getMarginEnd();
            int viewLeft = SearchViewAnimationHelper.this.getViewLeftFromSearchViewParent(SearchViewAnimationHelper.this.searchBar);
            if (!ViewUtils.isLayoutRtl(SearchViewAnimationHelper.this.searchBar)) {
                return ((SearchViewAnimationHelper.this.searchBar.getWidth() + viewLeft) + marginEnd) - SearchViewAnimationHelper.this.searchView.getWidth();
            }
            return viewLeft - marginEnd;
        }

        private int getFromTranslationY() {
            int toolbarMiddleY = SearchViewAnimationHelper.this.toolbarContainer.getTop() + (SearchViewAnimationHelper.this.toolbarContainer.getHeight() / 2);
            int searchBarMiddleY = SearchViewAnimationHelper.this.getViewTopFromSearchViewParent(SearchViewAnimationHelper.this.searchBar) + (SearchViewAnimationHelper.this.searchBar.getHeight() / 2);
            return searchBarMiddleY - toolbarMiddleY;
        }

        private AnimatorSet getButtonsTranslationAnimator(boolean show) {
            AnimatorSet animatorSet = new AnimatorSet();
            addBackButtonTranslationAnimatorIfNeeded(animatorSet);
            addActionMenuViewAnimatorIfNeeded(animatorSet);
            animatorSet.setDuration(show ? 300L : 250L);
            animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.of(show, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            return animatorSet;
        }

        private void addBackButtonTranslationAnimatorIfNeeded(AnimatorSet animatorSet) {
            ImageButton searchViewBackButton = ToolbarUtils.getNavigationIconButton(SearchViewAnimationHelper.this.toolbar);
            if (searchViewBackButton != null) {
                ImageButton searchBarBackButton = ToolbarUtils.getNavigationIconButton(SearchViewAnimationHelper.this.searchBar);
                ValueAnimator backButtonAnimatorX = ValueAnimator.ofFloat(SearchViewAnimationHelper.this.getTranslationXBetweenViews(searchBarBackButton, searchViewBackButton), 0.0f);
                backButtonAnimatorX.addUpdateListener(MultiViewUpdateListener.translationXListener(searchViewBackButton));
                ValueAnimator backButtonAnimatorY = ValueAnimator.ofFloat(getFromTranslationY(), 0.0f);
                backButtonAnimatorY.addUpdateListener(MultiViewUpdateListener.translationYListener(searchViewBackButton));
                animatorSet.playTogether(backButtonAnimatorX, backButtonAnimatorY);
            }
        }

        private void addActionMenuViewAnimatorIfNeeded(AnimatorSet animatorSet) {
            ActionMenuView searchViewActionMenuView = ToolbarUtils.getActionMenuView(SearchViewAnimationHelper.this.toolbar);
            if (searchViewActionMenuView != null) {
                ActionMenuView searchBarActionMenuView = ToolbarUtils.getActionMenuView(SearchViewAnimationHelper.this.searchBar);
                ValueAnimator actionMenuViewAnimatorX = ValueAnimator.ofFloat(SearchViewAnimationHelper.this.getTranslationXBetweenViews(searchBarActionMenuView, searchViewActionMenuView), 0.0f);
                actionMenuViewAnimatorX.addUpdateListener(MultiViewUpdateListener.translationXListener(searchViewActionMenuView));
                ValueAnimator actionMenuViewAnimatorY = ValueAnimator.ofFloat(getFromTranslationY(), 0.0f);
                actionMenuViewAnimatorY.addUpdateListener(MultiViewUpdateListener.translationYListener(searchViewActionMenuView));
                animatorSet.playTogether(actionMenuViewAnimatorX, actionMenuViewAnimatorY);
            }
        }
    }

    class ContainedAnimationDelegate implements AnimationDelegate {
        ContainedAnimationDelegate() {
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void setUpDummyToolbarIfNeeded() {
            setUpDummyTextViewIfNeeded();
            if (SearchViewAnimationHelper.this.searchBar.getBackground() != null && SearchViewAnimationHelper.this.searchBar.getBackground().getConstantState() != null) {
                SearchViewAnimationHelper.this.dummyToolbar.setBackground(SearchViewAnimationHelper.this.searchBar.getBackground().getConstantState().newDrawable());
            }
            Menu menu = SearchViewAnimationHelper.this.dummyToolbar.getMenu();
            if (menu != null) {
                menu.clear();
            }
            if (SearchViewAnimationHelper.this.shouldInflateDummyToolbar()) {
                SearchViewAnimationHelper.this.dummyToolbar.inflateMenu(SearchViewAnimationHelper.this.searchBar.getMenuResId());
                SearchViewAnimationHelper.this.setMenuItemsNotClickable(SearchViewAnimationHelper.this.dummyToolbar);
            }
        }

        private void setUpDummyTextViewIfNeeded() {
            TextView searchBarTextView = SearchViewAnimationHelper.this.searchBar.getTextView();
            SearchViewAnimationHelper.this.dummyTextView.setText(searchBarTextView.getText());
            SearchViewAnimationHelper.this.dummyTextView.setHint(searchBarTextView.getHint());
            SearchViewAnimationHelper.this.dummyTextView.setVisibility(0);
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public AnimatorSet getExpandCollapseAnimatorSet(boolean show) {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(getBackgroundAlphaAnimator(show), getContentAlphaAnimator(show), getToolbarAlphaAnimator(show), getDummyTextViewWidthAnimator(show), SearchViewAnimationHelper.this.getClearButtonAnimator(show), getSearchBarSiblingsTranslationAnimator(show));
            return animatorSet;
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public List<SpringAnimation> getExpandCollapseSpringAnimations(boolean show) {
            return Arrays.asList(getToolbarWidthSpringAnimation(show), getToolbarTranslationXSpringAnimation(show), getDummyToolbarWidthSpringAnimation(show), getDummyToolbarTranslationXSpringAnimation(show), getToolbarContainerTranslationYSpringAnimation(show), getEditTextTranslationXSpringAnimation(show), getDummyTextTranslationXSpringAnimation(show));
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void onAnimationStart(boolean show) {
            SearchViewAnimationHelper searchViewAnimationHelper = SearchViewAnimationHelper.this;
            if (show) {
                searchViewAnimationHelper.setBackgroundAlpha(0.0f);
                SearchViewAnimationHelper.this.toolbar.setAlpha(0.0f);
                SearchViewAnimationHelper.this.contentContainer.setAlpha(0.0f);
                SearchViewAnimationHelper.this.searchBar.setVisibility(4);
            } else {
                searchViewAnimationHelper.setBackgroundAlpha(1.0f);
                SearchViewAnimationHelper.this.contentContainer.setAlpha(1.0f);
            }
            SearchViewAnimationHelper.this.dummyToolbar.setVisibility(0);
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void onAnimationEnd(boolean show) {
            SearchViewAnimationHelper searchViewAnimationHelper = SearchViewAnimationHelper.this;
            if (show) {
                searchViewAnimationHelper.setBackgroundAlpha(1.0f);
                SearchViewAnimationHelper.this.contentContainer.setAlpha(1.0f);
            } else {
                searchViewAnimationHelper.setBackgroundAlpha(0.0f);
                SearchViewAnimationHelper.this.contentContainer.setAlpha(0.0f);
                SearchViewAnimationHelper.this.searchBar.setVisibility(0);
            }
            SearchViewAnimationHelper.this.dummyToolbar.setVisibility(4);
            setWidth(SearchViewAnimationHelper.this.dummyTextView, -2);
        }

        @Override // com.google.android.material.search.SearchViewAnimationHelper.AnimationDelegate
        public void startButtonsTranslationAnimation() {
        }

        private Animator getBackgroundAlphaAnimator(boolean show) {
            ValueAnimator animator = getAlphaValueAnimator(show);
            animator.setDuration(SearchViewAnimationHelper.this.durationShort2);
            animator.setStartDelay(show ? 0L : SearchViewAnimationHelper.this.durationShort1);
            SearchViewAnimationHelper searchViewAnimationHelper = SearchViewAnimationHelper.this;
            animator.setInterpolator(show ? searchViewAnimationHelper.standardDecelerateInterpolator : searchViewAnimationHelper.standardAccelerateInterpolator);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.search.SearchViewAnimationHelper$ContainedAnimationDelegate$$ExternalSyntheticLambda2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.m8867xdc9a02e0(valueAnimator);
                }
            });
            return animator;
        }

        /* JADX INFO: renamed from: lambda$getBackgroundAlphaAnimator$0$com-google-android-material-search-SearchViewAnimationHelper$ContainedAnimationDelegate, reason: not valid java name */
        /* synthetic */ void m8867xdc9a02e0(ValueAnimator animation) {
            SearchViewAnimationHelper.this.setBackgroundAlpha(((Float) animation.getAnimatedValue()).floatValue());
        }

        private Animator getContentAlphaAnimator(boolean show) {
            ValueAnimator animator = getAlphaValueAnimator(show);
            animator.setDuration(SearchViewAnimationHelper.this.durationShort2);
            animator.setStartDelay(show ? SearchViewAnimationHelper.this.durationShort1 : 0L);
            SearchViewAnimationHelper searchViewAnimationHelper = SearchViewAnimationHelper.this;
            animator.setInterpolator(show ? searchViewAnimationHelper.standardAccelerateInterpolator : searchViewAnimationHelper.standardDecelerateInterpolator);
            animator.addUpdateListener(MultiViewUpdateListener.alphaListener(SearchViewAnimationHelper.this.contentContainer));
            return animator;
        }

        private Animator getToolbarAlphaAnimator(boolean show) {
            ValueAnimator animator = getAlphaValueAnimator(show);
            animator.setDuration(SearchViewAnimationHelper.this.durationShort2);
            SearchViewAnimationHelper searchViewAnimationHelper = SearchViewAnimationHelper.this;
            animator.setInterpolator(show ? searchViewAnimationHelper.standardDecelerateInterpolator : searchViewAnimationHelper.standardAccelerateInterpolator);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.search.SearchViewAnimationHelper$ContainedAnimationDelegate$$ExternalSyntheticLambda1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.m8869x2edc5246(valueAnimator);
                }
            });
            return animator;
        }

        /* JADX INFO: renamed from: lambda$getToolbarAlphaAnimator$1$com-google-android-material-search-SearchViewAnimationHelper$ContainedAnimationDelegate, reason: not valid java name */
        /* synthetic */ void m8869x2edc5246(ValueAnimator animation) {
            SearchViewAnimationHelper.this.toolbar.setAlpha(((Float) animation.getAnimatedValue()).floatValue());
        }

        private ValueAnimator getAlphaValueAnimator(boolean show) {
            float[] fArr = {1.0f, 0.0f};
            if (!show) {
                return ValueAnimator.ofFloat(fArr);
            }
            // fill-array-data instruction
            fArr[0] = 0.0f;
            fArr[1] = 1.0f;
            return ValueAnimator.ofFloat(fArr);
        }

        private Animator getDummyTextViewWidthAnimator(boolean show) {
            SearchViewAnimationHelper searchViewAnimationHelper = SearchViewAnimationHelper.this;
            View from = show ? searchViewAnimationHelper.searchBar.getTextView() : searchViewAnimationHelper.editText;
            SearchViewAnimationHelper searchViewAnimationHelper2 = SearchViewAnimationHelper.this;
            View to = show ? searchViewAnimationHelper2.editText : searchViewAnimationHelper2.searchBar.getTextView();
            ValueAnimator animator = ValueAnimator.ofInt(from.getWidth(), to.getWidth());
            animator.setDuration(SearchViewAnimationHelper.this.durationShort2);
            SearchViewAnimationHelper searchViewAnimationHelper3 = SearchViewAnimationHelper.this;
            animator.setInterpolator(show ? searchViewAnimationHelper3.standardDecelerateInterpolator : searchViewAnimationHelper3.standardAccelerateInterpolator);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.search.SearchViewAnimationHelper$ContainedAnimationDelegate$$ExternalSyntheticLambda3
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    this.f$0.m8868x67b2b750(valueAnimator);
                }
            });
            return animator;
        }

        /* JADX INFO: renamed from: lambda$getDummyTextViewWidthAnimator$2$com-google-android-material-search-SearchViewAnimationHelper$ContainedAnimationDelegate, reason: not valid java name */
        /* synthetic */ void m8868x67b2b750(ValueAnimator animation) {
            setWidth(SearchViewAnimationHelper.this.dummyTextView, ((Integer) animation.getAnimatedValue()).intValue());
        }

        private Animator getSearchBarSiblingsTranslationAnimator(boolean show) {
            AnimatorSet animatorSet = new AnimatorSet();
            AppBarLayout appBarLayout = SearchViewAnimationHelper.this.searchBar.getAppBarLayoutParentIfExists();
            if (SearchViewAnimationHelper.this.searchBar == null || appBarLayout == null) {
                return animatorSet;
            }
            View startSiblingView = getStartSiblingView(appBarLayout);
            View endSiblingView = getEndSiblingView(appBarLayout);
            boolean isRtl = ViewUtils.isLayoutRtl(SearchViewAnimationHelper.this.searchBar);
            int appBarLayoutWidth = appBarLayout.getWidth();
            if (startSiblingView != null) {
                Rect startSiblingRect = ViewUtils.calculateOffsetRectFromBounds(appBarLayout, startSiblingView);
                float startSiblingTranslationX = isRtl ? appBarLayoutWidth - startSiblingRect.left : -startSiblingRect.right;
                animatorSet.playTogether(getSiblingTranslationAnimator(startSiblingView, show, startSiblingTranslationX));
                animatorSet.playTogether(getSiblingAlphaAnimator(startSiblingView, show));
            }
            if (endSiblingView != null) {
                Rect endSiblingRect = ViewUtils.calculateOffsetRectFromBounds(appBarLayout, endSiblingView);
                float endSiblingTranslationX = isRtl ? -endSiblingRect.right : appBarLayoutWidth - endSiblingRect.left;
                animatorSet.playTogether(getSiblingTranslationAnimator(endSiblingView, show, endSiblingTranslationX));
                animatorSet.playTogether(getSiblingAlphaAnimator(endSiblingView, show));
            }
            animatorSet.setDuration(SearchViewAnimationHelper.this.durationShort2);
            animatorSet.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
            return animatorSet;
        }

        View getStartSiblingView(AppBarLayout appBarLayout) {
            int startSiblingViewId = SearchViewAnimationHelper.this.searchBar.getStartSiblingViewId();
            if (startSiblingViewId != -1) {
                return appBarLayout.findViewById(startSiblingViewId);
            }
            return getToolbarNavigationIconButton();
        }

        View getEndSiblingView(AppBarLayout appBarLayout) {
            int endSiblingViewId = SearchViewAnimationHelper.this.searchBar.getEndSiblingViewId();
            if (endSiblingViewId != -1) {
                return appBarLayout.findViewById(endSiblingViewId);
            }
            return getToolbarActionMenuView();
        }

        private View getToolbarNavigationIconButton() {
            ViewParent parent = SearchViewAnimationHelper.this.searchBar.getParent();
            if (!(parent instanceof Toolbar)) {
                return null;
            }
            return ToolbarUtils.getNavigationIconButton((Toolbar) parent);
        }

        private View getToolbarActionMenuView() {
            ViewParent parent = SearchViewAnimationHelper.this.searchBar.getParent();
            if (!(parent instanceof Toolbar)) {
                return null;
            }
            return ToolbarUtils.getActionMenuView((Toolbar) parent);
        }

        private Animator getSiblingTranslationAnimator(View view, boolean show, float translationX) {
            float startX = show ? 0.0f : translationX;
            float endX = show ? translationX : 0.0f;
            ValueAnimator animator = ValueAnimator.ofFloat(startX, endX);
            animator.addUpdateListener(MultiViewUpdateListener.translationXListener(view));
            return animator;
        }

        private Animator getSiblingAlphaAnimator(View view, boolean show) {
            ValueAnimator animator = getAlphaValueAnimator(!show);
            animator.addUpdateListener(MultiViewUpdateListener.alphaListener(view));
            return animator;
        }

        private SpringAnimation getToolbarWidthSpringAnimation(final boolean show, final Toolbar toolbar) {
            int searchBarWidth = SearchViewAnimationHelper.this.searchBar.getWidth();
            int toolbarWidth = getToolbarWidth();
            int startWidth = show ? searchBarWidth : toolbarWidth;
            int endWidth = show ? toolbarWidth : searchBarWidth;
            SpringAnimation animation = getSpringAnimation(toolbar, getWidthViewProperty(), startWidth, endWidth);
            animation.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: com.google.android.material.search.SearchViewAnimationHelper$ContainedAnimationDelegate$$ExternalSyntheticLambda0
                @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
                public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
                    this.f$0.m8870xf6aa07b8(show, toolbar, dynamicAnimation, z, f, f2);
                }
            });
            return animation;
        }

        /* JADX INFO: renamed from: lambda$getToolbarWidthSpringAnimation$3$com-google-android-material-search-SearchViewAnimationHelper$ContainedAnimationDelegate, reason: not valid java name */
        /* synthetic */ void m8870xf6aa07b8(boolean show, Toolbar toolbar, DynamicAnimation dynamicAnimation, boolean canceled, float value, float velocity) {
            if (show) {
                setWidth(toolbar, -1);
            }
        }

        private SpringAnimation getToolbarWidthSpringAnimation(boolean show) {
            return getToolbarWidthSpringAnimation(show, SearchViewAnimationHelper.this.toolbar);
        }

        private SpringAnimation getDummyToolbarWidthSpringAnimation(boolean show) {
            return getToolbarWidthSpringAnimation(show, SearchViewAnimationHelper.this.dummyToolbar);
        }

        private int getToolbarWidth() {
            int containerWidth = SearchViewAnimationHelper.this.toolbarContainer.getWidth();
            int containerHorizontalPaddings = SearchViewAnimationHelper.this.toolbarContainer.getPaddingStart() + SearchViewAnimationHelper.this.toolbarContainer.getPaddingEnd();
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) SearchViewAnimationHelper.this.toolbar.getLayoutParams();
            int toolbarHorizontalMargins = lp.getMarginStart() + lp.getMarginEnd();
            return (containerWidth - containerHorizontalPaddings) - toolbarHorizontalMargins;
        }

        private SpringAnimation getToolbarTranslationXSpringAnimation(boolean show, Toolbar toolbar) {
            int translationX = getToolbarTranslationX(toolbar);
            int startTranslationX = show ? translationX : 0;
            int endTranslationX = show ? 0 : translationX;
            return getSpringAnimation(toolbar, SpringAnimation.TRANSLATION_X, startTranslationX, endTranslationX);
        }

        private SpringAnimation getToolbarTranslationXSpringAnimation(boolean show) {
            return getToolbarTranslationXSpringAnimation(show, SearchViewAnimationHelper.this.toolbar);
        }

        private SpringAnimation getDummyToolbarTranslationXSpringAnimation(boolean show) {
            return getToolbarTranslationXSpringAnimation(show, SearchViewAnimationHelper.this.dummyToolbar);
        }

        private int getToolbarTranslationX(Toolbar toolbar) {
            int searchBarLeft = SearchViewAnimationHelper.this.getViewLeftFromSearchViewParent(SearchViewAnimationHelper.this.searchBar);
            int toolbarContainerPaddingStart = SearchViewAnimationHelper.this.toolbarContainer.getPaddingStart();
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
            int toolbarMarginStart = lp.getMarginStart();
            if (ViewUtils.isLayoutRtl(SearchViewAnimationHelper.this.searchBar)) {
                return (SearchViewAnimationHelper.this.searchBar.getWidth() + searchBarLeft) - ((SearchViewAnimationHelper.this.toolbarContainer.getWidth() - toolbarContainerPaddingStart) - toolbarMarginStart);
            }
            return (searchBarLeft - toolbarContainerPaddingStart) - toolbarMarginStart;
        }

        private SpringAnimation getToolbarContainerTranslationYSpringAnimation(boolean show) {
            int translationY = getToolbarTranslationY();
            int startTranslationY = show ? translationY : 0;
            int endTranslationY = show ? 0 : translationY;
            return getSpringAnimation(SearchViewAnimationHelper.this.toolbarContainer, SpringAnimation.TRANSLATION_Y, startTranslationY, endTranslationY);
        }

        private SpringAnimation getEditTextTranslationXSpringAnimation(boolean show) {
            return getTextTranslationXSpringAnimation(show, SearchViewAnimationHelper.this.editText);
        }

        private SpringAnimation getDummyTextTranslationXSpringAnimation(boolean show) {
            return getTextTranslationXSpringAnimation(show, SearchViewAnimationHelper.this.dummyTextView);
        }

        private SpringAnimation getTextTranslationXSpringAnimation(boolean show, View view) {
            TextView textView = SearchViewAnimationHelper.this.searchBar.getPlaceholderTextView();
            if (TextUtils.isEmpty(textView.getText()) || show) {
                textView = SearchViewAnimationHelper.this.searchBar.getTextView();
            }
            float translationX = SearchViewAnimationHelper.this.getTranslationXBetweenViews(textView, view) - getToolbarTranslationX(SearchViewAnimationHelper.this.toolbar);
            if (ViewUtils.isLayoutRtl(SearchViewAnimationHelper.this.searchBar)) {
                translationX += textView.getWidth() - view.getWidth();
            }
            float startTranslationX = show ? translationX : 0.0f;
            float endTranslationX = show ? 0.0f : translationX;
            return getSpringAnimation(view, SpringAnimation.TRANSLATION_X, startTranslationX, endTranslationX);
        }

        private int getToolbarTranslationY() {
            int searchBarTop = SearchViewAnimationHelper.this.getViewTopFromSearchViewParent(SearchViewAnimationHelper.this.searchBar);
            int toolbarTop = SearchViewAnimationHelper.this.getViewTopFromSearchViewParent(SearchViewAnimationHelper.this.toolbar);
            return searchBarTop - toolbarTop;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setWidth(View view, int width) {
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            lp.width = width;
            view.setLayoutParams(lp);
        }

        private FloatPropertyCompat<View> getWidthViewProperty() {
            return new FloatPropertyCompat<View>("width") { // from class: com.google.android.material.search.SearchViewAnimationHelper.ContainedAnimationDelegate.1
                @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
                public float getValue(View view) {
                    return view.getWidth();
                }

                @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
                public void setValue(View view, float value) {
                    ContainedAnimationDelegate.this.setWidth(view, (int) value);
                }
            };
        }

        private SpringAnimation getSpringAnimation(View view, FloatPropertyCompat<View> viewProperty, float startValue, float endValue) {
            SpringAnimation animation = new SpringAnimation(view, viewProperty);
            SpringForce spring = MotionUtils.resolveThemeSpringForce(SearchViewAnimationHelper.this.context, R.attr.motionSpringFastSpatial, R.style.Motion_Material3_Spring_Standard_Default_Spatial);
            animation.setSpring(spring);
            animation.setStartValue(startValue);
            animation.getSpring().setFinalPosition(endValue);
            return animation;
        }
    }
}
