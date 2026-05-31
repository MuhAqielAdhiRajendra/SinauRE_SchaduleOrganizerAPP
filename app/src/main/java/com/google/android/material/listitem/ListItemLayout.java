package com.google.android.material.listitem;

import android.R;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import androidx.core.view.GravityCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes13.dex */
public class ListItemLayout extends FrameLayout {
    private static final int DEFAULT_SIGNIFICANT_VEL_THRESHOLD = 500;
    public static final int POSITION_FIRST = 0;
    public static final int POSITION_LAST = 2;
    public static final int POSITION_MIDDLE = 1;
    public static final int POSITION_SINGLE = 3;
    private static final int SETTLING_DURATION = 350;
    private RevealableListItem activeSwipeToRevealLayout;
    private View contentView;
    private GestureDetector gestureDetector;
    private int lastStableSwipeState;
    private boolean originalClipToPadding;
    private int originalContentViewLeft;
    private int[] positionState;
    private int revealViewOffset;
    private final StateSettlingTracker stateSettlingTracker;
    private View.AccessibilityDelegate swipeAccessibilityDelegate;
    private int swipeState;
    private View swipeToRevealLayoutLeft;
    private View swipeToRevealLayoutRight;
    private ViewDragHelper viewDragHelper;
    private static final int[] FIRST_STATE_SET = {R.attr.state_first};
    private static final int[] MIDDLE_STATE_SET = {R.attr.state_middle};
    private static final int[] LAST_STATE_SET = {R.attr.state_last};
    private static final int[] SINGLE_STATE_SET = {R.attr.state_single};
    private static final TimeInterpolator CUBIC_BEZIER_INTERPOLATOR = new PathInterpolator(0.42f, 1.67f, 0.21f, 0.9f);

    @Retention(RetentionPolicy.SOURCE)
    public @interface Position {
    }

    /* JADX INFO: Access modifiers changed from: private */
    class StateSettlingTracker {
        private final Runnable continueSettlingRunnable;
        private boolean isContinueSettlingRunnablePosted;
        private int targetRevealGravity;
        private int targetSwipeState;

        private StateSettlingTracker() {
            this.continueSettlingRunnable = new Runnable() { // from class: com.google.android.material.listitem.ListItemLayout$StateSettlingTracker$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m8846x75cf24c1();
                }
            };
        }

        /* JADX INFO: renamed from: lambda$new$0$com-google-android-material-listitem-ListItemLayout$StateSettlingTracker, reason: not valid java name */
        /* synthetic */ void m8846x75cf24c1() {
            this.isContinueSettlingRunnablePosted = false;
            if (ListItemLayout.this.viewDragHelper == null || !ListItemLayout.this.viewDragHelper.continueSettling(true)) {
                if (ListItemLayout.this.swipeState == 2) {
                    ListItemLayout.this.setSwipeStateInternal(this.targetSwipeState, this.targetRevealGravity);
                    return;
                }
                return;
            }
            continueSettlingToState(this.targetSwipeState, this.targetRevealGravity);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void continueSettlingToState(int targetSwipeState, int targetRevealGravity) {
            this.targetSwipeState = targetSwipeState;
            this.targetRevealGravity = targetRevealGravity;
            if (!this.isContinueSettlingRunnablePosted) {
                ListItemLayout.this.post(this.continueSettlingRunnable);
                this.isContinueSettlingRunnablePosted = true;
            }
        }
    }

    public ListItemLayout(Context context) {
        this(context, null);
    }

    public ListItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, com.google.android.material.R.attr.listItemLayoutStyle);
    }

    public ListItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, com.google.android.material.R.style.Widget_Material3_ListItemLayout);
    }

    public ListItemLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, defStyleRes), attrs, defStyleAttr);
        this.swipeState = 3;
        this.lastStableSwipeState = 3;
        this.stateSettlingTracker = new StateSettlingTracker();
        getContext();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int extraSpace) {
        if (this.positionState == null) {
            return super.onCreateDrawableState(extraSpace);
        }
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        return mergeDrawableStates(drawableState, this.positionState);
    }

    public void updateAppearance(int position, int itemCount) {
        if (position < 0 || itemCount < 0) {
            this.positionState = null;
        } else if (itemCount == 1) {
            this.positionState = SINGLE_STATE_SET;
        } else if (position == 0) {
            this.positionState = FIRST_STATE_SET;
        } else if (position == itemCount - 1) {
            this.positionState = LAST_STATE_SET;
        } else {
            this.positionState = MIDDLE_STATE_SET;
        }
        refreshDrawableState();
    }

    public void updateAppearance(int position) {
        switch (position) {
            case 0:
                this.positionState = FIRST_STATE_SET;
                break;
            case 1:
                this.positionState = MIDDLE_STATE_SET;
                break;
            case 2:
                this.positionState = LAST_STATE_SET;
                break;
            case 3:
                this.positionState = SINGLE_STATE_SET;
                break;
        }
        refreshDrawableState();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.ViewGroup
    public void addView(View view, int index, ViewGroup.LayoutParams params) {
        super.addView(view, index, params);
        if (view instanceof RevealableListItem) {
            if (ListItemUtils.isRightAligned(view)) {
                if (this.swipeToRevealLayoutRight != null) {
                    throw new UnsupportedOperationException("Only one RevealableListItem with end gravity is supported.");
                }
                this.swipeToRevealLayoutRight = view;
            } else {
                if (this.swipeToRevealLayoutLeft != null) {
                    throw new UnsupportedOperationException("Only one RevealableListItem with start gravity is supported.");
                }
                this.swipeToRevealLayoutLeft = view;
            }
            ((RevealableListItem) view).setRevealedWidth(0);
            view.setElevation(getElevation() - 1.0f);
            return;
        }
        if (this.contentView != null && (view instanceof SwipeableListItem)) {
            throw new UnsupportedOperationException("Only one SwipeableListItem view is allowed in a ListItemLayout.");
        }
        if (view instanceof SwipeableListItem) {
            this.contentView = view;
        }
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        if (child == this.swipeToRevealLayoutLeft) {
            this.swipeToRevealLayoutLeft = null;
        } else if (child == this.swipeToRevealLayoutRight) {
            this.swipeToRevealLayoutRight = null;
        } else if (this.contentView == child) {
            this.contentView = null;
        }
        if (!swipeToRevealLayoutExists() || this.contentView == null) {
            this.viewDragHelper = null;
            this.gestureDetector = null;
            this.swipeAccessibilityDelegate = null;
            setClipToPadding(this.originalClipToPadding);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (ensureSwipeToRevealSetupIfNeeded()) {
            this.viewDragHelper.processTouchEvent(ev);
            this.gestureDetector.onTouchEvent(ev);
            if (this.viewDragHelper.getViewDragState() == 1) {
                return true;
            }
        }
        return super.onTouchEvent(ev);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ensureSwipeToRevealSetupIfNeeded()) {
            int action = ev.getActionMasked();
            if (action == 3 || action == 1) {
                this.viewDragHelper.cancel();
                return false;
            }
            this.gestureDetector.onTouchEvent(ev);
            return this.viewDragHelper.shouldInterceptTouchEvent(ev);
        }
        return super.onInterceptTouchEvent(ev);
    }

    private boolean ensureSwipeToRevealSetupIfNeeded() {
        if (!swipeToRevealLayoutExists() || this.contentView == null) {
            return false;
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = createViewDragHelper();
        }
        if (this.gestureDetector == null) {
            this.gestureDetector = createGestureDetector();
        }
        if (this.swipeAccessibilityDelegate == null) {
            this.swipeAccessibilityDelegate = createSwipeAccessibilityDelegate();
            this.contentView.setAccessibilityDelegate(this.swipeAccessibilityDelegate);
        }
        if (getClipToPadding()) {
            this.originalClipToPadding = getClipToPadding();
            setClipToPadding(false);
            return true;
        }
        return true;
    }

    private ViewDragHelper createViewDragHelper() {
        return ViewDragHelper.create(this, new ViewDragHelper.Callback() { // from class: com.google.android.material.listitem.ListItemLayout.1
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View child, int pointerId) {
                if ((!(ListItemLayout.this.contentView instanceof SwipeableListItem) || ((SwipeableListItem) ListItemLayout.this.contentView).isSwipeEnabled()) && ListItemLayout.this.swipeToRevealLayoutExists() && ListItemLayout.this.contentView != null) {
                    ListItemLayout.this.viewDragHelper.captureChildView(ListItemLayout.this.contentView, pointerId);
                }
                return false;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if ((ListItemLayout.this.contentView instanceof SwipeableListItem) && ListItemLayout.this.swipeToRevealLayoutExists()) {
                    SwipeableListItem swipeableItem = (SwipeableListItem) ListItemLayout.this.contentView;
                    int minClamp = ListItemLayout.this.originalContentViewLeft;
                    int maxClamp = ListItemLayout.this.originalContentViewLeft;
                    if (ListItemLayout.this.swipeToRevealLayoutRight instanceof RevealableListItem) {
                        int maxDistance = calculateMaxSwipeDistance((RevealableListItem) ListItemLayout.this.swipeToRevealLayoutRight);
                        minClamp = ListItemLayout.this.originalContentViewLeft - (swipeableItem.getSwipeMaxOvershoot() + maxDistance);
                    }
                    if (ListItemLayout.this.swipeToRevealLayoutLeft instanceof RevealableListItem) {
                        int maxDistance2 = calculateMaxSwipeDistance((RevealableListItem) ListItemLayout.this.swipeToRevealLayoutLeft);
                        maxClamp = ListItemLayout.this.originalContentViewLeft + swipeableItem.getSwipeMaxOvershoot() + maxDistance2;
                    }
                    int maxDistance3 = Math.min(left, maxClamp);
                    return Math.max(minClamp, maxDistance3);
                }
                return 0;
            }

            /* JADX WARN: Multi-variable type inference failed */
            private int calculateMaxSwipeDistance(RevealableListItem revealableListItem) {
                int margin;
                ViewGroup.MarginLayoutParams revealViewLp = (ViewGroup.MarginLayoutParams) ((View) revealableListItem).getLayoutParams();
                if (revealableListItem.getPrimaryActionSwipeMode() != 0) {
                    ViewGroup.MarginLayoutParams contentViewLp = (ViewGroup.MarginLayoutParams) ListItemLayout.this.contentView.getLayoutParams();
                    if (ListItemUtils.isRightAligned((View) revealableListItem)) {
                        margin = contentViewLp.leftMargin;
                    } else {
                        margin = contentViewLp.rightMargin;
                    }
                    return ListItemLayout.this.contentView.getMeasuredWidth() + margin;
                }
                return revealableListItem.getIntrinsicWidth() + revealViewLp.getMarginStart() + revealViewLp.getMarginEnd();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int getViewHorizontalDragRange(View child) {
                if (ListItemLayout.this.contentView instanceof SwipeableListItem) {
                    SwipeableListItem item = (SwipeableListItem) ListItemLayout.this.contentView;
                    int range = ListItemLayout.this.swipeToRevealLayoutLeft instanceof RevealableListItem ? 0 + ((RevealableListItem) ListItemLayout.this.swipeToRevealLayoutLeft).getIntrinsicWidth() + item.getSwipeMaxOvershoot() : 0;
                    if (ListItemLayout.this.swipeToRevealLayoutRight instanceof RevealableListItem) {
                        return range + ((RevealableListItem) ListItemLayout.this.swipeToRevealLayoutRight).getIntrinsicWidth() + item.getSwipeMaxOvershoot();
                    }
                    return range;
                }
                return 0;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                if (ListItemLayout.this.viewDragHelper == null || !(ListItemLayout.this.contentView instanceof SwipeableListItem) || !ListItemLayout.this.swipeToRevealLayoutExists()) {
                    return;
                }
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                ListItemLayout.this.updateSwipeProgress(left);
                if (ListItemLayout.this.viewDragHelper.getViewDragState() == 1 && ListItemLayout.this.activeSwipeToRevealLayout != null) {
                    ListItemLayout.this.setSwipeStateInternal(1, ListItemLayout.this.getAbsoluteRevealGravity((View) ListItemLayout.this.activeSwipeToRevealLayout));
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                int currentLeft;
                if ((ListItemLayout.this.contentView instanceof SwipeableListItem) && ListItemLayout.this.swipeToRevealLayoutExists() && (currentLeft = releasedChild.getLeft()) != ListItemLayout.this.originalContentViewLeft) {
                    boolean isRevealingLeft = currentLeft > ListItemLayout.this.originalContentViewLeft;
                    int absoluteGravity = isRevealingLeft ? 3 : 5;
                    ListItemLayout listItemLayout = ListItemLayout.this;
                    RevealableListItem revealLayout = absoluteGravity == 3 ? (RevealableListItem) listItemLayout.swipeToRevealLayoutLeft : (RevealableListItem) listItemLayout.swipeToRevealLayoutRight;
                    if (revealLayout == null) {
                        return;
                    }
                    int targetSwipeState = calculateTargetSwipeState(absoluteGravity, revealLayout, xvel, currentLeft);
                    ListItemLayout.this.startSettling(ListItemLayout.this.contentView, targetSwipeState, absoluteGravity);
                }
            }

            private int calculateTargetSwipeState(int absoluteGravity, RevealableListItem revealLayout, float xvel, int swipeViewLeft) {
                if (!ListItemLayout.this.swipeToRevealLayoutExistsForGravity(absoluteGravity)) {
                    return 3;
                }
                float effectiveXvel = absoluteGravity == 3 ? xvel : -xvel;
                return calculateTargetSwipeStateForRevealLayout(swipeViewLeft, effectiveXvel, revealLayout, ListItemLayout.this.getSwipeRevealViewRevealedOffset(absoluteGravity), ListItemLayout.this.getSwipeToActionOffset(absoluteGravity));
            }

            private int calculateTargetSwipeStateForRevealLayout(int currentLeft, float effectiveXvel, RevealableListItem swipeToRevealLayout, int revealedOffset, int primaryActionOffset) {
                int targetOpenOffset;
                boolean primaryActionEnabled = swipeToRevealLayout.getPrimaryActionSwipeMode() != 0;
                boolean swipeDirectlyToPrimaryAction = swipeToRevealLayout.getPrimaryActionSwipeMode() == 2;
                if (effectiveXvel > 500.0f) {
                    if (primaryActionEnabled) {
                        return (ListItemLayout.this.lastStableSwipeState != 3 || swipeDirectlyToPrimaryAction) ? 5 : 4;
                    }
                    return 4;
                }
                if (effectiveXvel < -500.0f) {
                    return (swipeDirectlyToPrimaryAction || ListItemLayout.this.lastStableSwipeState != 5) ? 3 : 4;
                }
                if (primaryActionEnabled && Math.abs(currentLeft - primaryActionOffset) < Math.abs(currentLeft - revealedOffset)) {
                    return 5;
                }
                if (primaryActionEnabled && swipeDirectlyToPrimaryAction) {
                    targetOpenOffset = primaryActionOffset;
                } else {
                    targetOpenOffset = revealedOffset;
                }
                if (Math.abs(currentLeft - targetOpenOffset) < Math.abs(currentLeft - ListItemLayout.this.getSwipeViewClosedOffset())) {
                    return (primaryActionEnabled && swipeDirectlyToPrimaryAction) ? 5 : 4;
                }
                return 3;
            }
        });
    }

    private GestureDetector createGestureDetector() {
        return new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.google.android.material.listitem.ListItemLayout.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (ListItemLayout.this.getParent() != null) {
                    ListItemLayout.this.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
                return false;
            }
        });
    }

    private View.AccessibilityDelegate createSwipeAccessibilityDelegate() {
        return new View.AccessibilityDelegate() { // from class: com.google.android.material.listitem.ListItemLayout.3
            private void addSwipeAccessibilityActions(View revealLayout, AccessibilityNodeInfoCompat infoCompat) {
                if (revealLayout instanceof ViewGroup) {
                    ViewGroup revealViewGroup = (ViewGroup) revealLayout;
                    for (int i = 0; i < revealViewGroup.getChildCount(); i++) {
                        View child = revealViewGroup.getChildAt(i);
                        if (shouldAddAccessibilityAction(child)) {
                            infoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(getAccessibilityActionId(child), child.getContentDescription()));
                        }
                    }
                }
            }

            private boolean performRevealViewAction(View revealLayout, int action) {
                if (revealLayout instanceof ViewGroup) {
                    ViewGroup revealViewGroup = (ViewGroup) revealLayout;
                    for (int i = 0; i < revealViewGroup.getChildCount(); i++) {
                        View child = revealViewGroup.getChildAt(i);
                        if (getAccessibilityActionId(child) == action) {
                            return child.performClick();
                        }
                    }
                    return false;
                }
                return false;
            }

            private boolean shouldAddAccessibilityAction(View child) {
                return child.isClickable() && child.getContentDescription() != null && child.isEnabled() && child.getVisibility() == 0;
            }

            @Override // android.view.View.AccessibilityDelegate
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                AccessibilityNodeInfoCompat infoCompat = AccessibilityNodeInfoCompat.wrap(info);
                addSwipeAccessibilityActions(ListItemLayout.this.swipeToRevealLayoutLeft, infoCompat);
                addSwipeAccessibilityActions(ListItemLayout.this.swipeToRevealLayoutRight, infoCompat);
            }

            @Override // android.view.View.AccessibilityDelegate
            public boolean performAccessibilityAction(View host, int action, Bundle args) {
                if (performRevealViewAction(ListItemLayout.this.swipeToRevealLayoutLeft, action) || performRevealViewAction(ListItemLayout.this.swipeToRevealLayoutRight, action)) {
                    return true;
                }
                return super.performAccessibilityAction(host, action, args);
            }

            private int getAccessibilityActionId(View child) {
                return child.getId();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getSwipeRevealViewRevealedOffset(int gravity) {
        View revealLayout = isRevealGravityLeft(gravity) ? this.swipeToRevealLayoutLeft : this.swipeToRevealLayoutRight;
        if (revealLayout == null) {
            return 0;
        }
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) revealLayout.getLayoutParams();
        int revealViewTotalWidth = ((RevealableListItem) revealLayout).getIntrinsicWidth() + lp.leftMargin + lp.rightMargin;
        int direction = isRevealGravityLeft(gravity) ? 1 : -1;
        return this.originalContentViewLeft + (direction * revealViewTotalWidth);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getSwipeViewClosedOffset() {
        return this.originalContentViewLeft;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getSwipeToActionOffset(int revealedGravity) {
        if (this.contentView == null) {
            return 0;
        }
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.contentView.getLayoutParams();
        int width = this.contentView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
        int direction = isRevealGravityLeft(revealedGravity) ? 1 : -1;
        return this.originalContentViewLeft + (direction * width);
    }

    private boolean isRevealGravityLeft(int gravity) {
        return getAbsoluteHorizontalGravity(gravity) == 3;
    }

    private int getAbsoluteHorizontalGravity(int gravity) {
        int horizontalGravity = GravityCompat.getAbsoluteGravity(gravity, getLayoutDirection()) & 7;
        if (horizontalGravity == 3) {
            return 3;
        }
        if (horizontalGravity != 5 && getLayoutDirection() == 1) {
            return 3;
        }
        return 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean swipeToRevealLayoutExistsForGravity(int gravity) {
        maybeSwapRevealLayoutsForGravity();
        if (isRevealGravityLeft(gravity)) {
            return this.swipeToRevealLayoutLeft instanceof RevealableListItem;
        }
        return this.swipeToRevealLayoutRight instanceof RevealableListItem;
    }

    private int getOffsetForSwipeState(int swipeState, int revealGravity) {
        if (!swipeToRevealLayoutExistsForGravity(revealGravity)) {
            throw new IllegalArgumentException("No RevealableListItem with gravity " + revealGravity);
        }
        switch (swipeState) {
            case 3:
                return getSwipeViewClosedOffset();
            case 4:
                return getSwipeRevealViewRevealedOffset(revealGravity);
            case 5:
                return getSwipeToActionOffset(revealGravity);
            default:
                throw new IllegalArgumentException("Invalid state to get swipe offset: " + swipeState);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSwipeProgress(int left) {
        if (!(this.contentView instanceof SwipeableListItem) || !swipeToRevealLayoutExists()) {
            return;
        }
        this.revealViewOffset = left - this.originalContentViewLeft;
        boolean revealingLeft = this.revealViewOffset > 0;
        boolean revealingRight = this.revealViewOffset < 0;
        if (revealingLeft && (this.swipeToRevealLayoutLeft instanceof RevealableListItem)) {
            this.activeSwipeToRevealLayout = (RevealableListItem) this.swipeToRevealLayoutLeft;
        } else if (revealingRight && (this.swipeToRevealLayoutRight instanceof RevealableListItem)) {
            this.activeSwipeToRevealLayout = (RevealableListItem) this.swipeToRevealLayoutRight;
        }
        FrameLayout.LayoutParams contentViewLp = (FrameLayout.LayoutParams) this.contentView.getLayoutParams();
        if (this.swipeToRevealLayoutLeft instanceof RevealableListItem) {
            FrameLayout.LayoutParams revealViewLp = (FrameLayout.LayoutParams) this.swipeToRevealLayoutLeft.getLayoutParams();
            int revealViewDesiredWidth = Math.max(0, ((Math.abs(this.originalContentViewLeft - this.contentView.getLeft()) - contentViewLp.leftMargin) - revealViewLp.getMarginStart()) - revealViewLp.getMarginEnd());
            ((RevealableListItem) this.swipeToRevealLayoutLeft).setRevealedWidth(revealingLeft ? revealViewDesiredWidth : 0);
        }
        if (this.swipeToRevealLayoutRight instanceof RevealableListItem) {
            FrameLayout.LayoutParams revealViewLp2 = (FrameLayout.LayoutParams) this.swipeToRevealLayoutRight.getLayoutParams();
            int revealViewDesiredWidth2 = Math.max(0, ((Math.abs(this.originalContentViewLeft - this.contentView.getLeft()) - contentViewLp.rightMargin) - revealViewLp2.getMarginStart()) - revealViewLp2.getMarginEnd());
            ((RevealableListItem) this.swipeToRevealLayoutRight).setRevealedWidth(revealingRight ? revealViewDesiredWidth2 : 0);
        }
        ((SwipeableListItem) this.contentView).onSwipe(this.revealViewOffset);
        if (revealingRight && (this.swipeToRevealLayoutRight instanceof RevealableListItem)) {
            updateAlphaFade(getSwipeToActionOffset(5), getSwipeRevealViewRevealedOffset(5));
        } else if (revealingLeft && (this.swipeToRevealLayoutLeft instanceof RevealableListItem)) {
            updateAlphaFade(getSwipeToActionOffset(3), getSwipeRevealViewRevealedOffset(3));
        } else {
            this.contentView.setAlpha(1.0f);
        }
    }

    private void updateAlphaFade(int fullSwipedOffset, int revealedOffset) {
        int fadeOutThreshold;
        if (revealedOffset == fullSwipedOffset) {
            fadeOutThreshold = (getSwipeViewClosedOffset() + fullSwipedOffset) / 2;
        } else {
            fadeOutThreshold = (fullSwipedOffset + revealedOffset) / 2;
        }
        float contentViewAlpha = AnimationUtils.lerp(1.0f, 0.0f, (this.revealViewOffset - fadeOutThreshold) / (fullSwipedOffset - fadeOutThreshold));
        this.contentView.setAlpha(contentViewAlpha);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSettling(View contentView, int targetSwipeState, int revealGravity) {
        boolean settling;
        if (this.viewDragHelper == null) {
            return;
        }
        int left = getOffsetForSwipeState(targetSwipeState, revealGravity);
        ViewDragHelper viewDragHelper = this.viewDragHelper;
        if (targetSwipeState == 4) {
            settling = viewDragHelper.smoothSlideViewTo(contentView, left, contentView.getTop(), SETTLING_DURATION, (Interpolator) CUBIC_BEZIER_INTERPOLATOR);
        } else {
            settling = viewDragHelper.smoothSlideViewTo(contentView, left, contentView.getTop());
        }
        if (settling) {
            setSwipeStateInternal(2, revealGravity);
            this.stateSettlingTracker.continueSettlingToState(targetSwipeState, revealGravity);
        } else {
            setSwipeStateInternal(targetSwipeState, revealGravity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSwipeStateInternal(int swipeState, int revealLayoutGravity) {
        RevealableListItem revealableListItem;
        int revealLayoutGravity2 = getAbsoluteHorizontalGravity(revealLayoutGravity);
        if (swipeState == this.swipeState && (this.activeSwipeToRevealLayout == null || getAbsoluteRevealGravity((View) this.activeSwipeToRevealLayout) == revealLayoutGravity2)) {
            return;
        }
        if (swipeState != 3 && !swipeToRevealLayoutExistsForGravity(revealLayoutGravity2)) {
            return;
        }
        if (swipeState == 5 && (this.activeSwipeToRevealLayout == null || this.activeSwipeToRevealLayout.getPrimaryActionSwipeMode() == 0)) {
            return;
        }
        if (isRevealGravityLeft(revealLayoutGravity2)) {
            revealableListItem = (RevealableListItem) this.swipeToRevealLayoutLeft;
        } else {
            revealableListItem = (RevealableListItem) this.swipeToRevealLayoutRight;
        }
        this.activeSwipeToRevealLayout = revealableListItem;
        this.swipeState = swipeState;
        if (swipeState != 1 && swipeState != 2) {
            this.lastStableSwipeState = swipeState;
        }
        int originalGravity = revealLayoutGravity2;
        if (this.activeSwipeToRevealLayout != null) {
            originalGravity = ((FrameLayout.LayoutParams) ((View) this.activeSwipeToRevealLayout).getLayoutParams()).gravity;
        }
        ((SwipeableListItem) this.contentView).onSwipeStateChanged(swipeState, castToView(this.activeSwipeToRevealLayout), originalGravity == -1 ? GravityCompat.END : originalGravity);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T extends View & RevealableListItem> T castToView(RevealableListItem revealableListItem) {
        return (T) ((View) revealableListItem);
    }

    public <T extends View & RevealableListItem> void setSwipeState(int swipeState, T revealView) {
        setSwipeState(swipeState, (View) revealView, true);
    }

    public <T extends View & RevealableListItem> void setSwipeState(int swipeState, T revealView, boolean animate) {
        if (revealView != this.swipeToRevealLayoutLeft && revealView != this.swipeToRevealLayoutRight) {
            throw new IllegalArgumentException("revealView must be a child of ListItemLayout.");
        }
        setSwipeState(swipeState, ((FrameLayout.LayoutParams) revealView.getLayoutParams()).gravity, animate);
    }

    public void setSwipeState(int swipeState, int revealGravity) {
        setSwipeState(swipeState, revealGravity, true);
    }

    public void setSwipeState(final int swipeState, final int revealGravity, final boolean animate) {
        if (swipeState != 3 && swipeState != 4 && swipeState != 5) {
            throw new IllegalArgumentException("Invalid swipe state: " + swipeState);
        }
        if (!(this.contentView instanceof SwipeableListItem) || !swipeToRevealLayoutExists()) {
            throw new IllegalArgumentException("ListItemLayout must have a SwipeableListItem child and a RevealableListItem child to be swiped.");
        }
        if (swipeState != 3 && !swipeToRevealLayoutExistsForGravity(revealGravity)) {
            throw new IllegalArgumentException("No RevealableListItem is defined for the given gravity: " + revealGravity);
        }
        Runnable runnable = new Runnable() { // from class: com.google.android.material.listitem.ListItemLayout$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m8845x6b76121b(animate, swipeState, revealGravity);
            }
        };
        if (isLaidOut()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    /* JADX INFO: renamed from: lambda$setSwipeState$0$com-google-android-material-listitem-ListItemLayout, reason: not valid java name */
    /* synthetic */ void m8845x6b76121b(boolean animate, int swipeState, int revealGravity) {
        if (!animate) {
            if (this.viewDragHelper != null) {
                this.viewDragHelper.abort();
            }
            int finalLeft = getOffsetForSwipeState(swipeState, revealGravity);
            this.contentView.offsetLeftAndRight(finalLeft - this.contentView.getLeft());
            updateSwipeProgress(finalLeft);
            setSwipeStateInternal(swipeState, revealGravity);
            return;
        }
        startSettling(this.contentView, swipeState, revealGravity);
    }

    public int getSwipeState() {
        return this.swipeState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean swipeToRevealLayoutExists() {
        return (this.swipeToRevealLayoutLeft instanceof RevealableListItem) || (this.swipeToRevealLayoutRight instanceof RevealableListItem);
    }

    private void maybeSwapRevealLayoutsForGravity() {
        boolean leftIsMisaligned = this.swipeToRevealLayoutLeft != null && ListItemUtils.isRightAligned(this.swipeToRevealLayoutLeft);
        boolean rightIsMisaligned = (this.swipeToRevealLayoutRight == null || ListItemUtils.isRightAligned(this.swipeToRevealLayoutRight)) ? false : true;
        if (leftIsMisaligned && rightIsMisaligned) {
            View temp = this.swipeToRevealLayoutLeft;
            this.swipeToRevealLayoutLeft = this.swipeToRevealLayoutRight;
            this.swipeToRevealLayoutRight = temp;
            this.revealViewOffset *= -1;
            return;
        }
        if (leftIsMisaligned) {
            if (this.swipeToRevealLayoutRight != null) {
                throw new IllegalStateException("Cannot have more than one RevealableListItem with the same absolute gravity.");
            }
            this.swipeToRevealLayoutRight = this.swipeToRevealLayoutLeft;
            this.swipeToRevealLayoutLeft = null;
            this.revealViewOffset *= -1;
            return;
        }
        if (rightIsMisaligned) {
            if (this.swipeToRevealLayoutLeft != null) {
                throw new IllegalStateException("Cannot have more than one RevealableListItem with the same absolute gravity.");
            }
            this.swipeToRevealLayoutLeft = this.swipeToRevealLayoutRight;
            this.swipeToRevealLayoutRight = null;
            this.revealViewOffset *= -1;
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        maybeSwapRevealLayoutsForGravity();
        if (this.contentView != null && swipeToRevealLayoutExists() && ensureSwipeToRevealSetupIfNeeded()) {
            this.originalContentViewLeft = this.contentView.getLeft();
            int originalContentViewRight = this.contentView.getRight();
            this.contentView.offsetLeftAndRight(this.revealViewOffset);
            if (this.swipeToRevealLayoutLeft != null) {
                layoutRevealView(this.swipeToRevealLayoutLeft, this.originalContentViewLeft, originalContentViewRight);
            }
            if (this.swipeToRevealLayoutRight != null) {
                layoutRevealView(this.swipeToRevealLayoutRight, this.originalContentViewLeft, originalContentViewRight);
            }
        }
    }

    private void layoutRevealView(View swipeToRevealLayout, int contentLeft, int contentRight) {
        int swipeToRevealLeft;
        int swipeToRevealRight;
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) swipeToRevealLayout.getLayoutParams();
        if (ListItemUtils.isRightAligned(swipeToRevealLayout)) {
            swipeToRevealRight = contentRight - lp.rightMargin;
            swipeToRevealLeft = swipeToRevealRight - swipeToRevealLayout.getMeasuredWidth();
        } else {
            int swipeToRevealRight2 = lp.leftMargin;
            swipeToRevealLeft = contentLeft + swipeToRevealRight2;
            swipeToRevealRight = swipeToRevealLayout.getMeasuredWidth() + swipeToRevealLeft;
        }
        swipeToRevealLayout.layout(swipeToRevealLeft, swipeToRevealLayout.getTop(), swipeToRevealRight, swipeToRevealLayout.getBottom());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getAbsoluteRevealGravity(View revealView) {
        return ListItemUtils.isRightAligned(revealView) ? 5 : 3;
    }
}
