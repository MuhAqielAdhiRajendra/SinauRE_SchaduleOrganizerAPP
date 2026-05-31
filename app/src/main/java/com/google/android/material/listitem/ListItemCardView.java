package com.google.android.material.listitem;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes13.dex */
public class ListItemCardView extends MaterialCardView implements SwipeableListItem {
    private static final int[] SWIPED_STATE_SET = {R.attr.state_swiped};
    private boolean isSwiped;
    private final LinkedHashSet<SwipeCallback> swipeCallbacks;
    private boolean swipeEnabled;
    private final int swipeMaxOvershoot;

    public static abstract class SwipeCallback {
        public abstract void onSwipe(int i);

        public abstract <T extends View & RevealableListItem> void onSwipeStateChanged(int i, T t, int i2);
    }

    public ListItemCardView(Context context) {
        this(context, null);
    }

    public ListItemCardView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.listItemCardViewStyle);
    }

    public ListItemCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.Widget_Material3_ListItemCardView);
    }

    public ListItemCardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, defStyleRes), attrs, defStyleAttr);
        this.isSwiped = false;
        this.swipeCallbacks = new LinkedHashSet<>();
        Context context2 = getContext();
        this.swipeMaxOvershoot = getResources().getDimensionPixelSize(R.dimen.m3_list_max_swipe_overshoot);
        TintTypedArray attributes = ThemeEnforcement.obtainTintedStyledAttributes(context2, attrs, R.styleable.ListItemCardView, defStyleAttr, defStyleRes, new int[0]);
        this.swipeEnabled = attributes.getBoolean(R.styleable.ListItemCardView_swipeEnabled, true);
        attributes.recycle();
    }

    @Override // com.google.android.material.listitem.SwipeableListItem
    public int getSwipeMaxOvershoot() {
        return this.swipeMaxOvershoot;
    }

    @Override // com.google.android.material.listitem.SwipeableListItem
    public void setSwipeEnabled(boolean swipeEnabled) {
        this.swipeEnabled = swipeEnabled;
    }

    @Override // com.google.android.material.listitem.SwipeableListItem
    public boolean isSwipeEnabled() {
        return this.swipeEnabled;
    }

    @Override // com.google.android.material.card.MaterialCardView, android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (this.isSwiped) {
            mergeDrawableStates(drawableState, SWIPED_STATE_SET);
        }
        return drawableState;
    }

    public void addSwipeCallback(SwipeCallback callback) {
        this.swipeCallbacks.add(callback);
    }

    public void removeSwipeCallback(SwipeCallback callback) {
        this.swipeCallbacks.remove(callback);
    }

    @Override // com.google.android.material.listitem.SwipeableListItem
    public void onSwipe(int swipeOffset) {
        for (SwipeCallback callback : this.swipeCallbacks) {
            callback.onSwipe(swipeOffset);
        }
    }

    @Override // com.google.android.material.listitem.SwipeableListItem
    public <T extends View & RevealableListItem> void onSwipeStateChanged(int swipeState, T revealableListItem, int revealGravity) {
        this.isSwiped = swipeState != 3;
        refreshDrawableState();
        for (SwipeCallback callback : this.swipeCallbacks) {
            callback.onSwipeStateChanged(swipeState, revealableListItem, revealGravity);
        }
    }
}
