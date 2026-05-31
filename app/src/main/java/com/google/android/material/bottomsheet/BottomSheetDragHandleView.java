package com.google.android.material.bottomsheet;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import com.google.android.material.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* JADX INFO: loaded from: classes13.dex */
public class BottomSheetDragHandleView extends AppCompatImageView implements AccessibilityManager.AccessibilityStateChangeListener {
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_BottomSheet_DragHandle;
    private final AccessibilityManager accessibilityManager;
    private BottomSheetBehavior<?> bottomSheetBehavior;
    private final BottomSheetBehavior.BottomSheetCallback bottomSheetCallback;
    private final String clickToCollapseActionLabel;
    private boolean clickToExpand;
    private final String clickToExpandActionLabel;
    private final String clickToHalfExpandActionLabel;
    private final GestureDetector gestureDetector;
    private final GestureDetector.OnGestureListener gestureListener;
    private boolean hasClickListener;
    private boolean hasTouchListener;

    public BottomSheetDragHandleView(Context context) {
        this(context, null);
    }

    public BottomSheetDragHandleView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.bottomSheetDragHandleStyle);
    }

    public BottomSheetDragHandleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, DEF_STYLE_RES), attrs, defStyleAttr);
        this.hasTouchListener = false;
        this.hasClickListener = false;
        this.clickToExpandActionLabel = getResources().getString(R.string.bottomsheet_action_expand_description);
        this.clickToHalfExpandActionLabel = getResources().getString(R.string.bottomsheet_action_half_expand_description);
        this.clickToCollapseActionLabel = getResources().getString(R.string.bottomsheet_action_collapse_description);
        this.bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() { // from class: com.google.android.material.bottomsheet.BottomSheetDragHandleView.1
            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onStateChanged(View bottomSheet, int newState) {
                BottomSheetDragHandleView.this.onBottomSheetStateChanged(newState);
            }

            @Override // com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        };
        this.gestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.google.android.material.bottomsheet.BottomSheetDragHandleView.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent e) {
                return BottomSheetDragHandleView.this.isClickable();
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent e) {
                BottomSheetDragHandleView.this.performLongClick(e.getX(), e.getY());
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return BottomSheetDragHandleView.this.expandOrCollapseBottomSheetIfPossible();
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent e) {
                if (BottomSheetDragHandleView.this.bottomSheetBehavior != null && BottomSheetDragHandleView.this.bottomSheetBehavior.isHideable()) {
                    BottomSheetDragHandleView.this.bottomSheetBehavior.setState(5);
                    return true;
                }
                return super.onDoubleTap(e);
            }
        };
        Context context2 = getContext();
        if (Build.VERSION.SDK_INT >= 26) {
            setTooltipText(getResources().getString(R.string.bottomsheet_drag_handle_content_description));
        }
        this.gestureDetector = new GestureDetector(context2, this.gestureListener, new Handler(Looper.getMainLooper()));
        this.accessibilityManager = (AccessibilityManager) context2.getSystemService("accessibility");
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() { // from class: com.google.android.material.bottomsheet.BottomSheetDragHandleView.3
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onPopulateAccessibilityEvent(host, event);
                if (event.getEventType() == 1) {
                    BottomSheetDragHandleView.this.expandOrCollapseBottomSheetIfPossible();
                }
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                CharSequence newDescription;
                super.onInitializeAccessibilityNodeInfo(host, info);
                if (!BottomSheetDragHandleView.this.hasAttachedBehavior()) {
                    return;
                }
                CharSequence originalDescription = BottomSheetDragHandleView.this.getContentDescription();
                String stateName = null;
                switch (BottomSheetDragHandleView.this.bottomSheetBehavior.getState()) {
                    case 3:
                        stateName = BottomSheetDragHandleView.this.getResources().getString(R.string.bottomsheet_state_expanded);
                        break;
                    case 4:
                        stateName = BottomSheetDragHandleView.this.getResources().getString(R.string.bottomsheet_state_collapsed);
                        break;
                    case 6:
                        stateName = BottomSheetDragHandleView.this.getResources().getString(R.string.bottomsheet_state_half_expanded);
                        break;
                }
                if (!TextUtils.isEmpty(stateName)) {
                    if (TextUtils.isEmpty(originalDescription)) {
                        newDescription = stateName;
                    } else {
                        newDescription = stateName + ". " + ((Object) originalDescription);
                    }
                    info.setContentDescription(newDescription);
                }
            }
        });
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setBottomSheetBehavior(findParentBottomSheetBehavior());
        if (this.accessibilityManager != null) {
            this.accessibilityManager.addAccessibilityStateChangeListener(this);
            onAccessibilityStateChanged(this.accessibilityManager.isEnabled());
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        if (this.accessibilityManager != null) {
            this.accessibilityManager.removeAccessibilityStateChangeListener(this);
        }
        setBottomSheetBehavior(null);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (this.hasClickListener || this.hasTouchListener) {
            return super.onTouchEvent(event);
        }
        return this.gestureDetector.onTouchEvent(event);
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener l) {
        this.hasTouchListener = l != null;
        super.setOnTouchListener(l);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener l) {
        this.hasClickListener = l != null;
        super.setOnClickListener(l);
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public void onAccessibilityStateChanged(boolean enabled) {
    }

    private void setBottomSheetBehavior(BottomSheetBehavior<?> behavior) {
        if (this.bottomSheetBehavior != null) {
            this.bottomSheetBehavior.removeBottomSheetCallback(this.bottomSheetCallback);
            this.bottomSheetBehavior.setAccessibilityDelegateView(null);
            this.bottomSheetBehavior.setDragHandleView(null);
        }
        this.bottomSheetBehavior = behavior;
        if (this.bottomSheetBehavior != null) {
            this.bottomSheetBehavior.setAccessibilityDelegateView(this);
            this.bottomSheetBehavior.setDragHandleView(this);
            onBottomSheetStateChanged(this.bottomSheetBehavior.getState());
            this.bottomSheetBehavior.addBottomSheetCallback(this.bottomSheetCallback);
        }
        setClickable(hasAttachedBehavior());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBottomSheetStateChanged(int state) {
        if (state == 4) {
            this.clickToExpand = true;
        } else if (state == 3) {
            this.clickToExpand = false;
        }
        int nextState = getNextState();
        String text = null;
        switch (nextState) {
            case 3:
                text = this.clickToExpandActionLabel;
                break;
            case 4:
                text = this.clickToCollapseActionLabel;
                break;
            case 6:
                text = this.clickToHalfExpandActionLabel;
                break;
        }
        ViewCompat.replaceAccessibilityAction(this, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK, text, new AccessibilityViewCommand() { // from class: com.google.android.material.bottomsheet.BottomSheetDragHandleView$$ExternalSyntheticLambda0
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public final boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                return this.f$0.m8779xa7b4c95f(view, commandArguments);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$onBottomSheetStateChanged$0$com-google-android-material-bottomsheet-BottomSheetDragHandleView, reason: not valid java name */
    /* synthetic */ boolean m8779xa7b4c95f(View v, AccessibilityViewCommand.CommandArguments args) {
        return expandOrCollapseBottomSheetIfPossible();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasAttachedBehavior() {
        return this.bottomSheetBehavior != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean expandOrCollapseBottomSheetIfPossible() {
        if (!hasAttachedBehavior()) {
            return false;
        }
        int nextState = getNextState();
        if (nextState != -1) {
            this.bottomSheetBehavior.setState(nextState);
            return true;
        }
        return true;
    }

    private int getNextState() {
        int i;
        if (!hasAttachedBehavior()) {
            return -1;
        }
        boolean canHalfExpand = (this.bottomSheetBehavior.isFitToContents() || this.bottomSheetBehavior.shouldSkipHalfExpandedStateWhenDragging()) ? false : true;
        int currentState = this.bottomSheetBehavior.getState();
        switch (currentState) {
            case 3:
                if (!canHalfExpand) {
                    if (this.bottomSheetBehavior.canCollapse()) {
                    }
                }
                break;
            case 4:
                if (canHalfExpand) {
                    i = 6;
                } else {
                    i = 3;
                }
                int nextState = i;
                break;
            case 6:
                if (!this.clickToExpand) {
                    if (this.bottomSheetBehavior.canCollapse()) {
                    }
                }
                break;
        }
        return -1;
    }

    private BottomSheetBehavior<?> findParentBottomSheetBehavior() {
        View parent = this;
        while (true) {
            View parentView = getParentView(parent);
            parent = parentView;
            if (parentView != null) {
                ViewGroup.LayoutParams layoutParams = parent.getLayoutParams();
                if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                    CoordinatorLayout.Behavior<?> behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
                    if (behavior instanceof BottomSheetBehavior) {
                        return (BottomSheetBehavior) behavior;
                    }
                }
            } else {
                return null;
            }
        }
    }

    private static View getParentView(View view) {
        Object parent = view.getParent();
        if (parent instanceof View) {
            return (View) parent;
        }
        return null;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!isEnabled()) {
            return super.onKeyDown(keyCode, event);
        }
        switch (keyCode) {
            case 23:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                if (!this.hasClickListener) {
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
