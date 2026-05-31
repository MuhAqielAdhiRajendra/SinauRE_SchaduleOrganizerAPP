package com.google.android.material.overflow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.TooltipCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonGroup;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes13.dex */
public class OverflowLinearLayout extends LinearLayout {
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_OverflowLinearLayout;
    private final MaterialButton overflowButton;
    private boolean overflowButtonAdded;
    private final Set<View> overflowViews;

    public OverflowLinearLayout(Context context) {
        this(context, null);
    }

    public OverflowLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.overflowLinearLayoutStyle);
    }

    public OverflowLinearLayout(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, defStyleAttr, DEF_STYLE_RES), attributeSet, defStyleAttr);
        this.overflowButtonAdded = false;
        this.overflowViews = new LinkedHashSet();
        Context context2 = getContext();
        TintTypedArray attributes = ThemeEnforcement.obtainTintedStyledAttributes(context2, attributeSet, R.styleable.OverflowLinearLayout, defStyleAttr, DEF_STYLE_RES, new int[0]);
        Drawable overflowButtonDrawable = attributes.getDrawable(R.styleable.OverflowLinearLayout_overflowButtonIcon);
        attributes.recycle();
        this.overflowButton = (MaterialButton) LayoutInflater.from(context2).inflate(R.layout.m3_overflow_linear_layout_overflow_button, (ViewGroup) this, false);
        TooltipCompat.setTooltipText(this.overflowButton, getResources().getString(R.string.m3_overflow_linear_layout_button_tooltip_text));
        setOverflowButtonIcon(overflowButtonDrawable);
        if (this.overflowButton.getContentDescription() == null) {
            this.overflowButton.setContentDescription(context2.getString(R.string.m3_overflow_linear_layout_button_content_description));
        }
        int overflowMenuStyle = MaterialAttributes.resolveOrThrow(this, R.attr.overflowLinearLayoutPopupMenuStyle);
        final PopupMenu popupMenu = new PopupMenu(getContext(), this.overflowButton, 17, 0, overflowMenuStyle);
        final int overflowItemIconPadding = context2.getResources().getDimensionPixelOffset(R.dimen.m3_overflow_item_icon_horizontal_padding);
        this.overflowButton.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.overflow.OverflowLinearLayout$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m8849xc4d03947(popupMenu, overflowItemIconPadding, view);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$new$0$com-google-android-material-overflow-OverflowLinearLayout, reason: not valid java name */
    /* synthetic */ void m8849xc4d03947(PopupMenu popupMenu, int overflowItemIconPadding, View v) {
        handleOverflowButtonClick(popupMenu, overflowItemIconPadding);
    }

    public boolean isOverflowed() {
        return !this.overflowViews.isEmpty();
    }

    public Set<View> getOverflowedViews() {
        return this.overflowViews;
    }

    public void setOverflowButtonIcon(Drawable icon) {
        this.overflowButton.m8785x11712a47(icon);
    }

    public void setOverflowButtonIconResource(int iconResourceId) {
        this.overflowButton.setIconResource(iconResourceId);
    }

    public Drawable getOverflowButtonIcon() {
        return this.overflowButton.getIcon();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int atMostSize;
        int i = 0;
        boolean isHorizontal = getOrientation() == 0;
        int childCountWithoutOverflowButton = this.overflowButtonAdded ? getChildCount() - 1 : getChildCount();
        if (isHorizontal) {
            atMostSize = View.MeasureSpec.getSize(widthMeasureSpec);
        } else {
            atMostSize = View.MeasureSpec.getSize(heightMeasureSpec);
        }
        int childrenSize = 0;
        int overflowButtonSize = getOverflowButtonSize(isHorizontal, this.overflowButton, widthMeasureSpec, heightMeasureSpec);
        this.overflowButton.setVisibility(8);
        this.overflowViews.clear();
        boolean shouldShowOverflow = false;
        int childIndex = 0;
        while (true) {
            if (childIndex >= childCountWithoutOverflowButton) {
                break;
            }
            View child = getChildAt(childIndex);
            child.setVisibility(i);
            int childSize = getChildSize(isHorizontal, child, widthMeasureSpec, heightMeasureSpec);
            if (childrenSize + childSize + overflowButtonSize > atMostSize) {
                this.overflowViews.add(child);
            }
            if (childrenSize + childSize > atMostSize) {
                shouldShowOverflow = true;
                for (int removedIndex = childIndex + 1; removedIndex < childCountWithoutOverflowButton; removedIndex++) {
                    this.overflowViews.add(getChildAt(removedIndex));
                }
            } else {
                childrenSize += childSize;
                childIndex++;
                i = 0;
            }
        }
        if (shouldShowOverflow) {
            for (View view : this.overflowViews) {
                view.setVisibility(8);
            }
            if (!this.overflowButtonAdded) {
                addView(this.overflowButton);
                this.overflowButtonAdded = true;
            }
            this.overflowButton.setVisibility(0);
        } else {
            this.overflowButton.setVisibility(8);
            this.overflowViews.clear();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private int getChildSize(boolean isHorizontal, View child, int widthMeasureSpec, int heightMeasureSpec) {
        int childSize;
        int minimumHeight;
        measureChild(child, widthMeasureSpec, heightMeasureSpec);
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (isHorizontal) {
            childSize = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
        } else {
            childSize = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
        }
        if (childSize == 0) {
            if (isHorizontal) {
                minimumHeight = child.getMinimumWidth() + lp.leftMargin + lp.rightMargin;
            } else {
                minimumHeight = child.getMinimumHeight() + lp.topMargin + lp.bottomMargin;
            }
            int childSize2 = minimumHeight;
            return childSize2;
        }
        return childSize;
    }

    private int getOverflowButtonSize(boolean isHorizontal, View button, int widthMeasureSpec, int heightMeasureSpec) {
        measureChild(button, widthMeasureSpec, heightMeasureSpec);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) button.getLayoutParams();
        if (isHorizontal) {
            return button.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
        }
        return button.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
    }

    private void handleOverflowButtonClick(PopupMenu popupMenu, int overflowItemIconPadding) {
        int overflowItemIconPadding2;
        popupMenu.getMenu().clear();
        popupMenu.setForceShowIcon(true);
        for (final View view : this.overflowViews) {
            LayoutParams lp = (LayoutParams) view.getLayoutParams();
            CharSequence text = MaterialButtonGroup.OverflowUtils.getMenuItemText(view, lp.overflowText);
            final MenuItem item = popupMenu.getMenu().add(text);
            Drawable icon = lp.overflowIcon;
            if (icon == null) {
                overflowItemIconPadding2 = overflowItemIconPadding;
            } else {
                overflowItemIconPadding2 = overflowItemIconPadding;
                item.setIcon(new InsetDrawable(icon, overflowItemIconPadding2, 0, overflowItemIconPadding, 0));
            }
            if (view instanceof MaterialButton) {
                MaterialButton button = (MaterialButton) view;
                item.setCheckable(button.isCheckable());
                item.setChecked(button.isChecked());
            }
            item.setEnabled(view.isEnabled());
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.google.android.material.overflow.OverflowLinearLayout$$ExternalSyntheticLambda0
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    return OverflowLinearLayout.lambda$handleOverflowButtonClick$1(view, item, menuItem);
                }
            });
            overflowItemIconPadding = overflowItemIconPadding2;
        }
        popupMenu.show();
    }

    static /* synthetic */ boolean lambda$handleOverflowButtonClick$1(View view, MenuItem item, MenuItem menuItem) {
        view.performClick();
        if (item.isCheckable()) {
            item.setChecked(!item.isChecked());
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        if (getOrientation() == 0) {
            return new LayoutParams(-2, -2);
        }
        return new LayoutParams(-1, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        if (p instanceof LayoutParams) {
            return new LayoutParams(p);
        }
        if (p instanceof LinearLayout.LayoutParams) {
            return new LayoutParams((LinearLayout.LayoutParams) p);
        }
        if (p instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) p);
        }
        return new LayoutParams(p);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public Drawable overflowIcon;
        public CharSequence overflowText;

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.overflowIcon = null;
            this.overflowText = null;
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.OverflowLinearLayout_Layout);
            this.overflowIcon = attributes.getDrawable(R.styleable.OverflowLinearLayout_Layout_layout_overflowIcon);
            this.overflowText = attributes.getText(R.styleable.OverflowLinearLayout_Layout_layout_overflowText);
            attributes.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(int width, int height, float weight) {
            super(width, height, weight);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(int width, int height, float weight, Drawable overflowIcon, CharSequence overflowText) {
            super(width, height, weight);
            this.overflowIcon = null;
            this.overflowText = null;
            this.overflowIcon = overflowIcon;
            this.overflowText = overflowText;
        }

        public LayoutParams(ViewGroup.LayoutParams p) {
            super(p);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(LinearLayout.LayoutParams source) {
            super(source);
            this.overflowIcon = null;
            this.overflowText = null;
        }

        public LayoutParams(LayoutParams source) {
            super((LinearLayout.LayoutParams) source);
            this.overflowIcon = null;
            this.overflowText = null;
            this.overflowText = source.overflowText;
            this.overflowIcon = source.overflowIcon;
        }
    }
}
