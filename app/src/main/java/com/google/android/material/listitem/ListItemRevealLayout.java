package com.google.android.material.listitem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes13.dex */
public class ListItemRevealLayout extends ViewGroup implements RevealableListItem {
    private static final int UNSET = -1;
    private int intrinsicHeight;
    private int intrinsicWidth;
    private int minChildWidth;
    private int[] originalChildHeights;
    private int[] originalChildWidths;
    private int originalHeightMeasureSpec;
    private int originalWidthMeasureSpec;
    private int primaryActionSwipeMode;
    private int revealedWidth;
    private WeakReference<View> siblingSwipeableView;

    public ListItemRevealLayout(Context context) {
        this(context, null);
    }

    public ListItemRevealLayout(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.listItemRevealLayoutStyle);
    }

    public ListItemRevealLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.Widget_Material3_ListItemRevealLayout);
    }

    public ListItemRevealLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, defStyleRes), attrs, defStyleAttr);
        this.intrinsicWidth = -1;
        this.intrinsicHeight = -1;
        this.revealedWidth = 0;
        this.originalWidthMeasureSpec = -1;
        this.originalHeightMeasureSpec = -1;
        Context context2 = getContext();
        setClipToPadding(false);
        TintTypedArray attributes = ThemeEnforcement.obtainTintedStyledAttributes(context2, attrs, R.styleable.ListItemRevealLayout, defStyleAttr, defStyleRes, new int[0]);
        this.minChildWidth = attributes.getDimensionPixelSize(R.styleable.ListItemRevealLayout_minChildWidth, getResources().getDimensionPixelSize(R.dimen.m3_list_reveal_min_child_width));
        this.primaryActionSwipeMode = attributes.getInt(R.styleable.ListItemRevealLayout_primaryActionSwipeMode, 0);
        attributes.recycle();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int overswipeAllowance;
        int childCount = getChildCount();
        if (shouldRemeasureIntrinsicSizes(this.originalHeightMeasureSpec, heightMeasureSpec, this.intrinsicHeight) || shouldRemeasureIntrinsicSizes(this.originalWidthMeasureSpec, widthMeasureSpec, this.intrinsicWidth)) {
            this.originalHeightMeasureSpec = heightMeasureSpec;
            this.originalWidthMeasureSpec = widthMeasureSpec;
            measureIntrinsicSize(widthMeasureSpec, heightMeasureSpec);
            saveOriginalChildSizes(childCount);
        }
        if (this.siblingSwipeableView == null || this.siblingSwipeableView.get() == null) {
            this.siblingSwipeableView = new WeakReference<>(findSiblingSwipeableView());
        }
        if (this.siblingSwipeableView.get() != null) {
            overswipeAllowance = ((SwipeableListItem) this.siblingSwipeableView.get()).getSwipeMaxOvershoot();
        } else {
            overswipeAllowance = 0;
        }
        int fullRevealableWidth = calculateFullRevealableWidth();
        setVisibility(this.revealedWidth == 0 ? 4 : 0);
        if (this.revealedWidth == 0) {
            setMeasuredDimension(0, this.intrinsicHeight);
            return;
        }
        if (childCount == 0) {
            setMeasuredDimension(this.revealedWidth, this.intrinsicHeight);
        } else if (this.primaryActionSwipeMode != 0 && this.revealedWidth > this.intrinsicWidth + overswipeAllowance && fullRevealableWidth > this.intrinsicWidth) {
            measureByGrowingPrimarySwipeAction(fullRevealableWidth);
        } else {
            measureByPreservingSwipeActionRatios(childCount);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        float ratio;
        ListItemRevealLayout listItemRevealLayout = this;
        float ratio2 = listItemRevealLayout.revealedWidth >= listItemRevealLayout.intrinsicWidth ? 1.0f : listItemRevealLayout.revealedWidth / listItemRevealLayout.intrinsicWidth;
        int currentLeft = (int) (listItemRevealLayout.getPaddingLeft() * ratio2);
        int paddingTop = listItemRevealLayout.getPaddingTop();
        int count = listItemRevealLayout.getChildCount();
        int start = 0;
        int dir = 1;
        if (listItemRevealLayout.getLayoutDirection() == 1) {
            start = count - 1;
            dir = -1;
        }
        int i = 0;
        while (i < count) {
            int childIndex = (dir * i) + start;
            View child = listItemRevealLayout.getChildAt(childIndex);
            if (child.getVisibility() == 8) {
                ratio = ratio2;
            } else {
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                int childTop = lp.topMargin + paddingTop;
                int adjustedLeftMargin = (int) (lp.leftMargin * ratio2);
                int adjustedRightMargin = (int) (lp.rightMargin * ratio2);
                int childLeft = currentLeft + adjustedLeftMargin;
                ratio = ratio2;
                int currentLeft2 = currentLeft;
                int currentLeft3 = childTop + childHeight;
                child.layout(childLeft, childTop, childLeft + childWidth, currentLeft3);
                currentLeft = currentLeft2 + adjustedLeftMargin + childWidth + adjustedRightMargin;
            }
            i++;
            listItemRevealLayout = this;
            ratio2 = ratio;
        }
    }

    private boolean shouldRemeasureIntrinsicSizes(int originalMeasureSpec, int newMeasureSpec, int intrinsicSize) {
        if (intrinsicSize == -1) {
            return true;
        }
        if (originalMeasureSpec == newMeasureSpec) {
            return false;
        }
        int mode = View.MeasureSpec.getMode(newMeasureSpec);
        return (mode == 0 || (mode == 1073741824 && View.MeasureSpec.getSize(newMeasureSpec) == intrinsicSize)) ? false : true;
    }

    void measureIntrinsicSize(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMeasureSpec2;
        int heightMeasureSpec2;
        int maxHeight = 0;
        int childCount = getChildCount();
        int childState = 0;
        int i = 0;
        int totalWidth = 0;
        while (i < childCount) {
            View child = getChildAt(i);
            if (child.getVisibility() == 8) {
                widthMeasureSpec2 = widthMeasureSpec;
                heightMeasureSpec2 = heightMeasureSpec;
            } else {
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
                widthMeasureSpec2 = widthMeasureSpec;
                heightMeasureSpec2 = heightMeasureSpec;
                measureChildWithMargins(child, widthMeasureSpec2, totalWidth, heightMeasureSpec2, 0);
                totalWidth += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                maxHeight = Math.max(maxHeight, child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
                childState = combineMeasuredStates(childState, child.getMeasuredState());
            }
            i++;
            widthMeasureSpec = widthMeasureSpec2;
            heightMeasureSpec = heightMeasureSpec2;
        }
        int widthMeasureSpec3 = widthMeasureSpec;
        int heightMeasureSpec3 = heightMeasureSpec;
        int widthMeasureSpec4 = getPaddingLeft();
        int totalWidth2 = Math.max(totalWidth + widthMeasureSpec4 + getPaddingRight(), getSuggestedMinimumWidth());
        int heightSizeAndState = resolveSizeAndState(Math.max(maxHeight + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), heightMeasureSpec3, 0);
        int maxHeight2 = heightSizeAndState & ViewCompat.MEASURED_SIZE_MASK;
        this.intrinsicWidth = resolveSizeAndState(totalWidth2, widthMeasureSpec3, 0);
        this.intrinsicHeight = resolveSizeAndState(maxHeight2, heightMeasureSpec3, childState << 16);
    }

    private void measureByGrowingPrimarySwipeAction(int fullRevealableWidth) {
        boolean isRtl = getLayoutDirection() == 1;
        boolean expandFirst = ListItemUtils.isRightAligned(this) == isRtl;
        Integer targetChildIndex = expandFirst ? findFirstVisibleChildIndex() : findLastVisibleChildIndex();
        if (targetChildIndex != null) {
            int targetWidthMinusTargetChild = getPaddingStart() + getPaddingEnd();
            float progress = Math.max(0.0f, Math.min(1.0f, (this.revealedWidth - this.intrinsicWidth) / (fullRevealableWidth - this.intrinsicWidth)));
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                if (child.getVisibility() != 8 && i != targetChildIndex.intValue()) {
                    child.measure(View.MeasureSpec.makeMeasureSpec(AnimationUtils.lerp(Math.max(this.originalChildWidths[i], this.minChildWidth), this.minChildWidth, progress), 1073741824), View.MeasureSpec.makeMeasureSpec(this.originalChildHeights[i], 1073741824));
                    ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
                    targetWidthMinusTargetChild += lp.leftMargin + lp.rightMargin + this.minChildWidth;
                    if ((child instanceof MaterialButton) && ((MaterialButton) child).getIcon() != null) {
                        ((MaterialButton) child).getIcon().setAlpha(AnimationUtils.lerp(255, 0, progress));
                    }
                }
            }
            int i2 = targetChildIndex.intValue();
            View targetChild = getChildAt(i2);
            ViewGroup.MarginLayoutParams lp2 = (ViewGroup.MarginLayoutParams) targetChild.getLayoutParams();
            int targetChildAvailableWidth = ((fullRevealableWidth - targetWidthMinusTargetChild) - lp2.rightMargin) - lp2.leftMargin;
            int extraTargetChildWidth = Math.max(this.revealedWidth - fullRevealableWidth, 0);
            targetChild.measure(View.MeasureSpec.makeMeasureSpec(AnimationUtils.lerp(this.originalChildWidths[targetChildIndex.intValue()], targetChildAvailableWidth, progress) + extraTargetChildWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(this.originalChildHeights[targetChildIndex.intValue()], 1073741824));
            if ((targetChild instanceof MaterialButton) && ((MaterialButton) targetChild).getIcon() != null) {
                ((MaterialButton) targetChild).getIcon().setAlpha(255);
            }
        }
        int extraTargetChildWidth2 = this.revealedWidth;
        setMeasuredDimension(extraTargetChildWidth2, this.intrinsicHeight);
    }

    private void measureByPreservingSwipeActionRatios(int childCount) {
        int materialButtonAlpha = (int) AnimationUtils.lerp(0.0f, 255.0f, this.intrinsicWidth / 4.0f, this.intrinsicWidth / 2.0f, this.revealedWidth);
        float ratio = this.revealedWidth / this.intrinsicWidth;
        int realWidth = 0;
        int adjustedPaddingLeft = (int) (getPaddingLeft() * ratio);
        int adjustedPaddingRight = (int) (getPaddingRight() * ratio);
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                int childWidth = Math.max(this.minChildWidth, (int) (this.originalChildWidths[i] * ratio));
                child.measure(View.MeasureSpec.makeMeasureSpec(childWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(this.originalChildHeights[i], 1073741824));
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
                int adjustedLeftMargin = (int) (lp.leftMargin * ratio);
                int adjustedRightMargin = (int) (lp.rightMargin * ratio);
                realWidth += childWidth + adjustedLeftMargin + adjustedRightMargin;
                if ((child instanceof MaterialButton) && ((MaterialButton) child).getIcon() != null) {
                    ((MaterialButton) child).getIcon().setAlpha(materialButtonAlpha);
                }
            }
        }
        int i2 = this.revealedWidth;
        setMeasuredDimension(Math.max(i2, realWidth + adjustedPaddingLeft + adjustedPaddingRight), this.intrinsicHeight);
    }

    private void saveOriginalChildSizes(int childCount) {
        this.originalChildWidths = new int[childCount];
        this.originalChildHeights = new int[childCount];
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                this.originalChildWidths[i] = child.getMeasuredWidth();
                this.originalChildHeights[i] = child.getMeasuredHeight();
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
                if (lp.height == -1) {
                    this.originalChildHeights[i] = this.intrinsicHeight;
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new ViewGroup.MarginLayoutParams(getContext(), attrs);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new ViewGroup.MarginLayoutParams(p);
    }

    public void resetIntrinsicWidth() {
        this.intrinsicWidth = -1;
        requestLayout();
    }

    @Override // com.google.android.material.listitem.RevealableListItem
    public int getIntrinsicWidth() {
        if (this.intrinsicWidth != -1) {
            return this.intrinsicWidth;
        }
        return 0;
    }

    @Override // com.google.android.material.listitem.RevealableListItem
    public void setRevealedWidth(int revealedWidth) {
        int revealedWidth2 = Math.max(0, revealedWidth);
        if (this.revealedWidth == revealedWidth2) {
            return;
        }
        this.revealedWidth = revealedWidth2;
        requestLayout();
    }

    private int calculateFullRevealableWidth() {
        if (this.siblingSwipeableView != null && this.siblingSwipeableView.get() != null) {
            return this.siblingSwipeableView.get().getMeasuredWidth();
        }
        if (getParent() instanceof View) {
            return ((View) getParent()).getMeasuredWidth();
        }
        return this.intrinsicWidth;
    }

    private View findSiblingSwipeableView() {
        if (!(getParent() instanceof ViewGroup)) {
            return null;
        }
        ViewGroup parent = (ViewGroup) getParent();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            if (child instanceof SwipeableListItem) {
                return child;
            }
        }
        return null;
    }

    public void setMinChildWidth(int minChildWidth) {
        if (this.minChildWidth == minChildWidth) {
            return;
        }
        this.minChildWidth = minChildWidth;
        requestLayout();
    }

    public int getMinChildWidth() {
        return this.minChildWidth;
    }

    private Integer findLastVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            if (getChildAt(i).getVisibility() != 8) {
                return Integer.valueOf(i);
            }
        }
        return null;
    }

    private Integer findFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).getVisibility() != 8) {
                return Integer.valueOf(i);
            }
        }
        return null;
    }

    @Override // com.google.android.material.listitem.RevealableListItem
    public void setPrimaryActionSwipeMode(int primaryActionSwipeMode) {
        this.primaryActionSwipeMode = primaryActionSwipeMode;
    }

    @Override // com.google.android.material.listitem.RevealableListItem
    public int getPrimaryActionSwipeMode() {
        return this.primaryActionSwipeMode;
    }
}
