package com.google.android.material.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.view.GravityCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearance;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.StateListCornerSize;
import com.google.android.material.shape.StateListShapeAppearanceModel;
import com.google.android.material.shape.StateListSizeChange;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes13.dex */
public class MaterialButtonGroup extends LinearLayout {
    private static final String LOG_TAG = "MButtonGroup";
    public static final int OVERFLOW_MODE_MENU = 1;
    public static final int OVERFLOW_MODE_NONE = 0;
    public static final int OVERFLOW_MODE_WRAP = 2;
    private boolean buttonOverflowInitialized;
    private StateListSizeChange buttonSizeChange;
    private final Map<Button, MenuItem> buttonToMenuItemMapping;
    private Integer[] childOrder;
    private final Comparator<MaterialButton> childOrderComparator;
    private boolean childShapesDirty;
    private StateListShapeAppearanceModel groupStateListShapeAppearance;
    StateListCornerSize innerCornerSize;
    private final List<ShapeAppearance> originalChildShapeAppearanceModels;
    private MaterialButton overflowButton;
    private final List<Button> overflowButtonsList;
    private final int overflowMenuItemIconPadding;
    private int overflowMode;
    private PopupMenu popupMenu;
    private final Map<Integer, Button> popupMenuItemToButtonMapping;
    private final PressedStateTracker pressedStateTracker;
    private final List<Integer> rowButtonFirstIndices;
    private int spacing;
    private final List<Button> tempOverflowButtonsList;
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_MaterialButtonGroup;
    public static final Object OVERFLOW_BUTTON_TAG = new Object();

    @Retention(RetentionPolicy.SOURCE)
    public @interface OverflowMode {
    }

    /* JADX INFO: renamed from: lambda$new$0$com-google-android-material-button-MaterialButtonGroup, reason: not valid java name */
    /* synthetic */ int m8787xa7af0300(MaterialButton v1, MaterialButton v2) {
        int checked = Boolean.valueOf(v1.isChecked()).compareTo(Boolean.valueOf(v2.isChecked()));
        if (checked != 0) {
            return checked;
        }
        int stateful = Boolean.valueOf(v1.isPressed()).compareTo(Boolean.valueOf(v2.isPressed()));
        if (stateful != 0) {
            return stateful;
        }
        return Integer.compare(indexOfChild(v1), indexOfChild(v2));
    }

    public MaterialButtonGroup(Context context) {
        this(context, null);
    }

    public MaterialButtonGroup(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.materialButtonGroupStyle);
    }

    public MaterialButtonGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(MaterialThemeOverlay.wrap(context, attrs, defStyleAttr, DEF_STYLE_RES), attrs, defStyleAttr);
        this.overflowMode = 0;
        this.originalChildShapeAppearanceModels = new ArrayList();
        this.pressedStateTracker = new PressedStateTracker();
        this.childOrderComparator = new Comparator() { // from class: com.google.android.material.button.MaterialButtonGroup$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.f$0.m8787xa7af0300((MaterialButton) obj, (MaterialButton) obj2);
            }
        };
        this.childShapesDirty = true;
        this.popupMenuItemToButtonMapping = new HashMap();
        this.buttonToMenuItemMapping = new HashMap();
        this.tempOverflowButtonsList = new ArrayList();
        this.overflowButtonsList = new ArrayList();
        this.rowButtonFirstIndices = new ArrayList();
        Context context2 = getContext();
        TypedArray attributes = ThemeEnforcement.obtainStyledAttributes(context2, attrs, R.styleable.MaterialButtonGroup, defStyleAttr, DEF_STYLE_RES, new int[0]);
        if (attributes.hasValue(R.styleable.MaterialButtonGroup_buttonSizeChange)) {
            this.buttonSizeChange = StateListSizeChange.create(context2, attributes, R.styleable.MaterialButtonGroup_buttonSizeChange);
        }
        if (attributes.hasValue(R.styleable.MaterialButtonGroup_shapeAppearance)) {
            this.groupStateListShapeAppearance = StateListShapeAppearanceModel.create(context2, attributes, R.styleable.MaterialButtonGroup_shapeAppearance);
            if (this.groupStateListShapeAppearance == null) {
                this.groupStateListShapeAppearance = new StateListShapeAppearanceModel.Builder(ShapeAppearanceModel.builder(context2, attributes.getResourceId(R.styleable.MaterialButtonGroup_shapeAppearance, 0), attributes.getResourceId(R.styleable.MaterialButtonGroup_shapeAppearanceOverlay, 0)).build()).build();
            }
        }
        if (attributes.hasValue(R.styleable.MaterialButtonGroup_innerCornerSize)) {
            this.innerCornerSize = StateListCornerSize.create(context2, attributes, R.styleable.MaterialButtonGroup_innerCornerSize, new AbsoluteCornerSize(0.0f));
        }
        this.spacing = attributes.getDimensionPixelSize(R.styleable.MaterialButtonGroup_android_spacing, 0);
        setChildrenDrawingOrderEnabled(true);
        setEnabled(attributes.getBoolean(R.styleable.MaterialButtonGroup_android_enabled, true));
        setOverflowMode(attributes.getInt(R.styleable.MaterialButtonGroup_overflowMode, 0));
        this.overflowMenuItemIconPadding = getResources().getDimensionPixelOffset(R.dimen.m3_btn_group_overflow_item_icon_horizontal_padding);
        if (isOverflowMenuSupported()) {
            initializeButtonOverflow(context2, attributes);
        }
        attributes.recycle();
    }

    boolean isOverflowMenuSupported() {
        return true;
    }

    void initializeButtonOverflow(Context context, TypedArray attributes) {
        Drawable overflowButtonDrawable = attributes.getDrawable(R.styleable.MaterialButtonGroup_overflowButtonIcon);
        this.overflowButton = (MaterialButton) LayoutInflater.from(context).inflate(R.layout.m3_button_group_overflow_button, (ViewGroup) this, false);
        this.overflowButton.setTag(OVERFLOW_BUTTON_TAG);
        setOverflowButtonIcon(overflowButtonDrawable);
        if (this.overflowButton.getContentDescription() == null) {
            this.overflowButton.setContentDescription(getResources().getString(R.string.mtrl_button_overflow_icon_content_description));
        }
        this.overflowButton.setVisibility(8);
        int overflowMenuStyle = MaterialAttributes.resolveOrThrow(this, R.attr.materialButtonGroupPopupMenuStyle);
        this.popupMenu = new PopupMenu(getContext(), this.overflowButton, 17, 0, overflowMenuStyle);
        this.popupMenu.setForceShowIcon(true);
        this.overflowButton.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.button.MaterialButtonGroup$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m8786x38fdbfa1(view);
            }
        });
        addView(this.overflowButton);
        this.buttonOverflowInitialized = true;
    }

    /* JADX INFO: renamed from: lambda$initializeButtonOverflow$1$com-google-android-material-button-MaterialButtonGroup, reason: not valid java name */
    /* synthetic */ void m8786x38fdbfa1(View v) {
        updateOverflowMenuItemsState();
        this.popupMenu.show();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        updateChildOrder();
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (!(child instanceof MaterialButton)) {
            Log.e(LOG_TAG, "Child views must be of type MaterialButton.");
            return;
        }
        recoverAllChildrenLayoutParams();
        this.childShapesDirty = true;
        int overflowButtonIndex = indexOfChild(this.overflowButton);
        if (overflowButtonIndex >= 0 && index == -1) {
            super.addView(child, overflowButtonIndex, params);
        } else {
            super.addView(child, index, params);
        }
        MaterialButton buttonChild = (MaterialButton) child;
        setGeneratedIdIfNeeded(buttonChild);
        buttonChild.setOnPressedChangeListenerInternal(this.pressedStateTracker);
        this.originalChildShapeAppearanceModels.add(buttonChild.getShapeAppearance());
        buttonChild.setEnabled(isEnabled());
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        if (child instanceof MaterialButton) {
            ((MaterialButton) child).setOnPressedChangeListenerInternal(null);
        }
        int indexOfChild = indexOfChild(child);
        if (indexOfChild >= 0) {
            this.originalChildShapeAppearanceModels.remove(indexOfChild);
        }
        this.childShapesDirty = true;
        updateChildShapes();
        recoverAllChildrenLayoutParams();
        adjustChildMarginsAndUpdateLayout();
    }

    public ShapeAppearanceModel getChildOriginalShapeAppearanceModel(int index) {
        return this.originalChildShapeAppearanceModels.get(index).getDefaultShape();
    }

    private void recoverAllChildrenLayoutParams() {
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton child = getChildButton(i);
            child.recoverOriginalLayoutParams();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        adjustChildMarginsAndUpdateLayout();
        int wrappedHeight = 0;
        if (this.overflowMode == 2) {
            if (getOrientation() == 1) {
                throw new IllegalArgumentException("The wrap overflow mode is not compatible to the vertical orientation.");
            }
            if (View.MeasureSpec.getMode(widthMeasureSpec) == Integer.MIN_VALUE) {
                throw new IllegalArgumentException("The wrap overflow mode is not compatible with wrap_content layout width.");
            }
            wrappedHeight = maybeWrapButtons(widthMeasureSpec, heightMeasureSpec);
        }
        maybeUpdateOverflowMenu(widthMeasureSpec, heightMeasureSpec);
        updateChildShapes();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.overflowMode == 2 && wrappedHeight != getMeasuredHeight()) {
            setMeasuredDimension(getMeasuredWidth(), wrappedHeight);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            recoverAllChildrenLayoutParams();
            adjustChildSizeChange();
        }
    }

    private int maybeWrapButtons(int widthMeasureSpec, int heightMeasureSpec) {
        int availableWidth;
        MaterialButtonGroup materialButtonGroup = this;
        materialButtonGroup.rowButtonFirstIndices.clear();
        int availableWidth2 = View.MeasureSpec.getSize(widthMeasureSpec);
        List<Integer> currentRowButtonIndices = new ArrayList<>();
        int rowWidth = 0;
        int rowHeight = 0;
        List<Integer> rowWidthList = new ArrayList<>();
        int prevRowsHeight = 0;
        int childIndex = 0;
        while (childIndex < materialButtonGroup.getChildCount()) {
            if (materialButtonGroup.isChildVisible(childIndex)) {
                MaterialButton child = materialButtonGroup.getChildButton(childIndex);
                materialButtonGroup.measureChild(child, widthMeasureSpec, heightMeasureSpec);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                if (childWidth > 0) {
                    LinearLayout.LayoutParams params = materialButtonGroup.buildLayoutParams(child);
                    int rowWidthWithCurrentChild = rowWidth + childWidth + (currentRowButtonIndices.isEmpty() ? 0 : materialButtonGroup.spacing);
                    if (rowWidthWithCurrentChild > availableWidth2 || currentRowButtonIndices.isEmpty()) {
                        if (!currentRowButtonIndices.isEmpty()) {
                            rowWidthList.add(Integer.valueOf(rowWidth));
                        }
                        prevRowsHeight += (materialButtonGroup.rowButtonFirstIndices.isEmpty() ? 0 : materialButtonGroup.spacing) + rowHeight;
                        availableWidth = availableWidth2;
                        materialButtonGroup.rowButtonFirstIndices.add(Integer.valueOf(childIndex));
                        params.setMarginStart(-rowWidth);
                        currentRowButtonIndices.clear();
                        rowWidth = 0;
                        rowHeight = 0;
                    } else {
                        availableWidth = availableWidth2;
                    }
                    rowWidth += childWidth + (rowWidth != 0 ? materialButtonGroup.spacing : 0);
                    int rowHeight2 = Math.max(rowHeight, childHeight);
                    currentRowButtonIndices.add(Integer.valueOf(childIndex));
                    params.topMargin += prevRowsHeight;
                    child.setLayoutParams(params);
                    rowHeight = rowHeight2;
                }
                childIndex++;
                availableWidth2 = availableWidth;
            }
            availableWidth = availableWidth2;
            childIndex++;
            availableWidth2 = availableWidth;
        }
        rowWidthList.add(Integer.valueOf(rowWidth));
        int lastOffset = 0;
        int maxRowWidth = ((Integer) Collections.max(rowWidthList)).intValue();
        int i = 0;
        while (i < materialButtonGroup.rowButtonFirstIndices.size()) {
            int rowFirstButtonIndex = materialButtonGroup.rowButtonFirstIndices.get(i).intValue();
            int rowWidth2 = rowWidthList.get(i).intValue();
            MaterialButton childButton = materialButtonGroup.getChildButton(rowFirstButtonIndex);
            LinearLayout.LayoutParams params2 = materialButtonGroup.buildLayoutParams(childButton);
            int horizontalRelativeGravity = params2.gravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
            int horizontalAbsoluteGravity = Gravity.getAbsoluteGravity(horizontalRelativeGravity, materialButtonGroup.getLayoutDirection());
            int offset = maxRowWidth - rowWidth2;
            if (horizontalRelativeGravity != 8388611) {
                if (horizontalAbsoluteGravity == 1) {
                    offset /= 2;
                }
                params2.setMarginStart((params2.getMarginStart() + offset) - lastOffset);
                childButton.setLayoutParams(params2);
                lastOffset = offset;
            }
            i++;
            materialButtonGroup = this;
        }
        return prevRowsHeight + rowHeight + getPaddingTop() + getPaddingBottom();
    }

    private void maybeUpdateOverflowMenu(int widthMeasureSpec, int heightMeasureSpec) {
        int availableSize;
        if (!this.buttonOverflowInitialized) {
            return;
        }
        if (this.overflowMode != 1) {
            this.overflowButton.setVisibility(8);
            return;
        }
        boolean isHorizontal = getOrientation() == 0;
        this.tempOverflowButtonsList.clear();
        if (isHorizontal) {
            availableSize = View.MeasureSpec.getSize(widthMeasureSpec);
        } else {
            availableSize = View.MeasureSpec.getSize(heightMeasureSpec);
        }
        int overflowButtonSize = measureAndGetChildButtonSize(isHorizontal, this.overflowButton, widthMeasureSpec, heightMeasureSpec);
        int currentDisplayedSize = 0;
        boolean shouldShowOverflow = false;
        int childIndex = 0;
        while (true) {
            if (childIndex >= getChildCount() - 1) {
                break;
            }
            Button child = getChildButton(childIndex);
            int childSize = measureAndGetChildButtonSize(isHorizontal, child, widthMeasureSpec, heightMeasureSpec);
            if (currentDisplayedSize + childSize + overflowButtonSize > availableSize) {
                this.tempOverflowButtonsList.add(child);
            }
            if (currentDisplayedSize + childSize > availableSize) {
                shouldShowOverflow = true;
                for (int childIndex2 = childIndex + 1; childIndex2 < getChildCount() - 1; childIndex2++) {
                    this.tempOverflowButtonsList.add(getChildButton(childIndex2));
                }
            } else {
                currentDisplayedSize += childSize;
                childIndex++;
            }
        }
        MaterialButton materialButton = this.overflowButton;
        if (shouldShowOverflow) {
            materialButton.setVisibility(0);
        } else {
            materialButton.setVisibility(8);
            this.tempOverflowButtonsList.clear();
        }
        maybeUpdateOverflowMenuItemsAndChildVisibility();
    }

    private void maybeUpdateOverflowMenuItemsAndChildVisibility() {
        if (this.tempOverflowButtonsList.equals(this.overflowButtonsList)) {
            return;
        }
        for (int i = 0; i < getChildCount() - 1; i++) {
            Button child = getChildButton(i);
            if (this.buttonToMenuItemMapping.containsKey(child)) {
                child.setVisibility(0);
            }
        }
        this.overflowButtonsList.clear();
        this.overflowButtonsList.addAll(this.tempOverflowButtonsList);
        Menu menu = this.popupMenu.getMenu();
        this.popupMenuItemToButtonMapping.clear();
        this.buttonToMenuItemMapping.clear();
        menu.clear();
        for (Button child2 : this.overflowButtonsList) {
            MenuItem item = addMenuItemForButton(menu, child2);
            if (item != null) {
                this.popupMenuItemToButtonMapping.put(Integer.valueOf(item.getItemId()), child2);
                this.buttonToMenuItemMapping.put(child2, item);
                child2.setVisibility(8);
            }
        }
        updateOverflowMenuItemsState();
    }

    private int measureAndGetChildButtonSize(boolean isHorizontal, Button button, int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int i2;
        measureChild(button, widthMeasureSpec, heightMeasureSpec);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) button.getLayoutParams();
        int containerSize = isHorizontal ? button.getMeasuredWidth() : button.getMeasuredHeight();
        if (isHorizontal) {
            i = lp.leftMargin;
            i2 = lp.rightMargin;
        } else {
            i = lp.topMargin;
            i2 = lp.bottomMargin;
        }
        int margins = i + i2;
        if (containerSize == 0) {
            containerSize = isHorizontal ? button.getMinimumWidth() : button.getMinimumHeight();
        }
        return containerSize + margins;
    }

    private MenuItem addMenuItemForButton(Menu menu, final Button button) {
        if (!(button.getLayoutParams() instanceof LayoutParams)) {
            return null;
        }
        LayoutParams lp = (LayoutParams) button.getLayoutParams();
        CharSequence text = OverflowUtils.getMenuItemText(button, lp.overflowText);
        Drawable icon = lp.overflowIcon;
        MenuItem item = menu.add(text);
        if (icon != null) {
            item.setIcon(new InsetDrawable(icon, this.overflowMenuItemIconPadding, 0, this.overflowMenuItemIconPadding, 0));
        }
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.google.android.material.button.MaterialButtonGroup$$ExternalSyntheticLambda2
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return MaterialButtonGroup.lambda$addMenuItemForButton$2(button, menuItem);
            }
        });
        return item;
    }

    static /* synthetic */ boolean lambda$addMenuItemForButton$2(Button button, MenuItem menuItem) {
        button.performClick();
        return true;
    }

    private void updateOverflowMenuItemsState() {
        for (Map.Entry<Button, MenuItem> entry : this.buttonToMenuItemMapping.entrySet()) {
            Button button = entry.getKey();
            MenuItem item = entry.getValue();
            if (entry.getKey() instanceof MaterialButton) {
                MaterialButton materialButton = (MaterialButton) button;
                item.setCheckable(materialButton.isCheckable());
                item.setChecked(materialButton.isChecked());
            }
            item.setEnabled(button.isEnabled());
        }
    }

    void updateChildShapes() {
        boolean isFirstVisible;
        boolean isLastVisible;
        boolean isHorizontal;
        ShapeAppearance defaultShape;
        if ((this.innerCornerSize != null || this.groupStateListShapeAppearance != null) && this.childShapesDirty) {
            this.childShapesDirty = false;
            int childCount = getChildCount();
            int firstVisibleChildIndex = getFirstVisibleChildIndex();
            int lastVisibleChildIndex = getLastVisibleChildIndex();
            for (int i = 0; i < childCount; i++) {
                MaterialButton button = getChildButton(i);
                if (button.getVisibility() != 8) {
                    if (i != firstVisibleChildIndex) {
                        isFirstVisible = false;
                    } else {
                        isFirstVisible = true;
                    }
                    if (i != lastVisibleChildIndex) {
                        isLastVisible = false;
                    } else {
                        isLastVisible = true;
                    }
                    StateListShapeAppearanceModel.Builder originalStateListShapeBuilder = getOriginalStateListShapeBuilder(isFirstVisible, isLastVisible, i);
                    if (getOrientation() != 0) {
                        isHorizontal = false;
                    } else {
                        isHorizontal = true;
                    }
                    boolean isRtl = ViewUtils.isLayoutRtl(this);
                    int cornerPositionBitsToKeep = 0;
                    if (isHorizontal) {
                        if (isFirstVisible) {
                            cornerPositionBitsToKeep = 0 | 5;
                        }
                        if (isLastVisible) {
                            cornerPositionBitsToKeep |= 10;
                        }
                        if (isRtl) {
                            cornerPositionBitsToKeep = StateListShapeAppearanceModel.swapCornerPositionRtl(cornerPositionBitsToKeep);
                        }
                    } else {
                        if (isFirstVisible) {
                            cornerPositionBitsToKeep = 0 | 3;
                        }
                        if (isLastVisible) {
                            cornerPositionBitsToKeep |= 12;
                        }
                    }
                    int cornerPositionBitsToOverride = ~cornerPositionBitsToKeep;
                    StateListShapeAppearanceModel newStateListShape = originalStateListShapeBuilder.setCornerSizeOverride(this.innerCornerSize, cornerPositionBitsToOverride).build();
                    if (newStateListShape.isStateful()) {
                        defaultShape = newStateListShape;
                    } else {
                        defaultShape = newStateListShape.getDefaultShape(true);
                    }
                    button.setShapeAppearance(defaultShape);
                }
            }
        }
    }

    private StateListShapeAppearanceModel.Builder getOriginalStateListShapeBuilder(boolean isFirstVisible, boolean isLastVisible, int index) {
        ShapeAppearance originalStateList;
        if (this.groupStateListShapeAppearance != null && (isFirstVisible || isLastVisible)) {
            originalStateList = this.groupStateListShapeAppearance;
        } else {
            originalStateList = this.originalChildShapeAppearanceModels.get(index);
        }
        if (!(originalStateList instanceof StateListShapeAppearanceModel)) {
            return new StateListShapeAppearanceModel.Builder((ShapeAppearanceModel) this.originalChildShapeAppearanceModels.get(index));
        }
        return ((StateListShapeAppearanceModel) originalStateList).toBuilder();
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int childCount, int i) {
        if (this.childOrder == null || i >= this.childOrder.length) {
            Log.w(LOG_TAG, "Child order wasn't updated");
            return i;
        }
        return this.childOrder[i].intValue();
    }

    private void adjustChildMarginsAndUpdateLayout() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i = firstVisibleChildIndex + 1; i < getChildCount(); i++) {
            int smallestStrokeWidth = 0;
            View currentChild = getChildAt(i);
            View previousChild = getChildAt(i - 1);
            if ((currentChild instanceof MaterialButton) && (previousChild instanceof MaterialButton)) {
                MaterialButton currentButton = (MaterialButton) currentChild;
                MaterialButton previousButton = (MaterialButton) previousChild;
                if (this.spacing <= 0) {
                    smallestStrokeWidth = Math.min(currentButton.getStrokeWidth(), previousButton.getStrokeWidth());
                    currentButton.setShouldDrawSurfaceColorStroke(true);
                    previousButton.setShouldDrawSurfaceColorStroke(true);
                } else {
                    currentButton.setShouldDrawSurfaceColorStroke(false);
                    previousButton.setShouldDrawSurfaceColorStroke(false);
                }
            }
            LinearLayout.LayoutParams params = buildLayoutParams(currentChild);
            if (getOrientation() == 0) {
                params.setMarginEnd(0);
                params.setMarginStart(this.spacing - smallestStrokeWidth);
                params.topMargin = 0;
            } else {
                params.bottomMargin = 0;
                params.topMargin = this.spacing - smallestStrokeWidth;
                params.setMarginStart(0);
            }
            currentChild.setLayoutParams(params);
        }
        resetChildMargins(firstVisibleChildIndex);
    }

    private void resetChildMargins(int childIndex) {
        if (getChildCount() == 0 || childIndex == -1) {
            return;
        }
        MaterialButton currentButton = getChildButton(childIndex);
        LinearLayout.LayoutParams params = buildLayoutParams(currentButton);
        if (getOrientation() == 1) {
            params.topMargin = 0;
            params.bottomMargin = 0;
        } else {
            params.setMarginEnd(0);
            params.setMarginStart(0);
            params.leftMargin = 0;
            params.rightMargin = 0;
        }
    }

    void onButtonWidthChanged(MaterialButton button, int increaseSize) {
        int buttonIndex = indexOfChild(button);
        if (buttonIndex < 0) {
            return;
        }
        MaterialButton prevVisibleButton = getPrevVisibleChildButton(buttonIndex, true);
        MaterialButton nextVisibleButton = getNextVisibleChildButton(buttonIndex, true);
        if (prevVisibleButton == null && nextVisibleButton == null) {
            return;
        }
        if (prevVisibleButton == null) {
            nextVisibleButton.setDisplayedWidthDecrease(increaseSize);
        }
        if (nextVisibleButton == null) {
            prevVisibleButton.setDisplayedWidthDecrease(increaseSize);
        }
        if (prevVisibleButton != null && nextVisibleButton != null) {
            prevVisibleButton.setDisplayedWidthDecrease(increaseSize / 2);
            nextVisibleButton.setDisplayedWidthDecrease((increaseSize + 1) / 2);
        }
    }

    private void adjustChildSizeChange() {
        int iIntValue;
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        if (firstVisibleChildIndex == -1 || this.buttonSizeChange == null) {
            return;
        }
        if (this.overflowMode == 2) {
            for (int i = 0; i < this.rowButtonFirstIndices.size(); i++) {
                int iIntValue2 = this.rowButtonFirstIndices.get(i).intValue();
                if (i == this.rowButtonFirstIndices.size() - 1) {
                    iIntValue = getChildCount();
                } else {
                    iIntValue = this.rowButtonFirstIndices.get(i + 1).intValue();
                }
                adjustChildSizeChangeInRange(iIntValue2, iIntValue - 1);
            }
            return;
        }
        adjustChildSizeChangeInRange(firstVisibleChildIndex, lastVisibleChildIndex);
    }

    private void adjustChildSizeChangeInRange(int start, int end) {
        MaterialButton.WidthChangeDirection widthChangeDirection;
        if (start == end) {
            getChildButton(start).setWidthChangeDirection(MaterialButton.WidthChangeDirection.NONE);
            return;
        }
        int widthIncreaseOnSingleEdge = Integer.MAX_VALUE;
        int i = start;
        while (i <= end) {
            if (isChildVisible(i)) {
                MaterialButton childButton = getChildButton(i);
                if (i == start) {
                    widthChangeDirection = MaterialButton.WidthChangeDirection.END;
                } else {
                    widthChangeDirection = i == end ? MaterialButton.WidthChangeDirection.START : MaterialButton.WidthChangeDirection.BOTH;
                }
                childButton.setWidthChangeDirection(widthChangeDirection);
                int widthIncrease = getButtonAllowedWidthIncrease(i);
                widthIncreaseOnSingleEdge = Math.min(widthIncreaseOnSingleEdge, (i == start || i == end) ? widthIncrease : widthIncrease / 2);
            }
            i++;
        }
        for (int i2 = start; i2 <= end; i2++) {
            if (isChildVisible(i2)) {
                MaterialButton child = getChildButton(i2);
                child.setSizeChange(this.buttonSizeChange);
                child.setWidthChangeMax(widthIncreaseOnSingleEdge * 2);
            }
        }
    }

    private int getButtonAllowedWidthIncrease(int index) {
        if (!isChildVisible(index) || this.buttonSizeChange == null) {
            return 0;
        }
        MaterialButton currentButton = getChildButton(index);
        int widthIncrease = Math.max(0, this.buttonSizeChange.getMaxWidthChange(currentButton.getWidth()));
        MaterialButton prevVisibleButton = getPrevVisibleChildButton(index, true);
        int prevButtonAllowedWidthDecrease = prevVisibleButton == null ? 0 : prevVisibleButton.getAllowedWidthDecrease();
        MaterialButton nextVisibleButton = getNextVisibleChildButton(index, true);
        int nextButtonAllowedWidthDecrease = nextVisibleButton != null ? nextVisibleButton.getAllowedWidthDecrease() : 0;
        return Math.min(widthIncrease, prevButtonAllowedWidthDecrease + nextButtonAllowedWidthDecrease);
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int orientation) {
        if (getOrientation() != orientation) {
            this.childShapesDirty = true;
        }
        super.setOrientation(orientation);
    }

    public StateListSizeChange getButtonSizeChange() {
        return this.buttonSizeChange;
    }

    public void setButtonSizeChange(StateListSizeChange buttonSizeChange) {
        if (this.buttonSizeChange != buttonSizeChange) {
            this.buttonSizeChange = buttonSizeChange;
            adjustChildSizeChange();
            requestLayout();
            invalidate();
        }
    }

    public int getSpacing() {
        return this.spacing;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
        invalidate();
        requestLayout();
    }

    public CornerSize getInnerCornerSize() {
        return this.innerCornerSize.getDefaultCornerSize();
    }

    public void setInnerCornerSize(CornerSize cornerSize) {
        this.innerCornerSize = StateListCornerSize.create(cornerSize);
        this.childShapesDirty = true;
        updateChildShapes();
        invalidate();
    }

    public StateListCornerSize getInnerCornerSizeStateList() {
        return this.innerCornerSize;
    }

    public void setInnerCornerSizeStateList(StateListCornerSize cornerSizeStateList) {
        this.innerCornerSize = cornerSizeStateList;
        this.childShapesDirty = true;
        updateChildShapes();
        invalidate();
    }

    public ShapeAppearanceModel getShapeAppearance() {
        if (this.groupStateListShapeAppearance == null) {
            return null;
        }
        return this.groupStateListShapeAppearance.getDefaultShape(true);
    }

    public void setShapeAppearance(ShapeAppearanceModel shapeAppearance) {
        this.groupStateListShapeAppearance = new StateListShapeAppearanceModel.Builder(shapeAppearance).build();
        this.childShapesDirty = true;
        updateChildShapes();
        invalidate();
    }

    public StateListShapeAppearanceModel getStateListShapeAppearance() {
        return this.groupStateListShapeAppearance;
    }

    public void setStateListShapeAppearance(StateListShapeAppearanceModel stateListShapeAppearance) {
        this.groupStateListShapeAppearance = stateListShapeAppearance;
        this.childShapesDirty = true;
        updateChildShapes();
        invalidate();
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

    public void setOverflowMode(int overflowMode) {
        if (this.overflowMode != overflowMode) {
            this.overflowMode = overflowMode;
            requestLayout();
            invalidate();
        }
    }

    public int getOverflowMode() {
        return this.overflowMode;
    }

    MaterialButton getChildButton(int index) {
        return (MaterialButton) getChildAt(index);
    }

    LinearLayout.LayoutParams buildLayoutParams(View child) {
        ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            return (LinearLayout.LayoutParams) layoutParams;
        }
        return new LayoutParams(layoutParams.width, layoutParams.height);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (isChildVisible(i)) {
                return i;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            if (isChildVisible(i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isChildVisible(int i) {
        View child = getChildAt(i);
        return child.getVisibility() != 8;
    }

    private void setGeneratedIdIfNeeded(MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            materialButton.setId(View.generateViewId());
        }
    }

    private MaterialButton getNextVisibleChildButton(int index) {
        return getNextVisibleChildButton(index, false);
    }

    private MaterialButton getNextVisibleChildButton(int index, boolean inSameRow) {
        int childCount = getChildCount();
        int nextVisibleButtonIndex = -1;
        int i = index + 1;
        while (true) {
            if (i >= childCount) {
                break;
            }
            if (!isChildVisible(i)) {
                i++;
            } else {
                nextVisibleButtonIndex = i;
                break;
            }
        }
        if (inSameRow && !this.rowButtonFirstIndices.isEmpty()) {
            for (int i2 = 0; i2 < this.rowButtonFirstIndices.size(); i2++) {
                int start = this.rowButtonFirstIndices.get(i2).intValue();
                int end = i2 == this.rowButtonFirstIndices.size() - 1 ? childCount - 1 : this.rowButtonFirstIndices.get(i2 + 1).intValue() - 1;
                if (index >= start && index <= end && (nextVisibleButtonIndex < start || nextVisibleButtonIndex > end)) {
                    return null;
                }
            }
        }
        if (nextVisibleButtonIndex == -1) {
            return null;
        }
        return getChildButton(nextVisibleButtonIndex);
    }

    private MaterialButton getPrevVisibleChildButton(int index) {
        return getPrevVisibleChildButton(index, false);
    }

    private MaterialButton getPrevVisibleChildButton(int index, boolean inSameRow) {
        int childCount = getChildCount();
        int prevVisibleButtonIndex = -1;
        int i = index - 1;
        while (true) {
            if (i < 0) {
                break;
            }
            if (!isChildVisible(i)) {
                i--;
            } else {
                prevVisibleButtonIndex = i;
                break;
            }
        }
        if (inSameRow && !this.rowButtonFirstIndices.isEmpty()) {
            int i2 = 0;
            while (i2 < this.rowButtonFirstIndices.size()) {
                int start = this.rowButtonFirstIndices.get(i2).intValue();
                int nextStart = i2 == this.rowButtonFirstIndices.size() + (-1) ? childCount : this.rowButtonFirstIndices.get(i2 + 1).intValue();
                if (index >= start && index < nextStart && (prevVisibleButtonIndex < start || prevVisibleButtonIndex >= nextStart)) {
                    return null;
                }
                i2++;
            }
        }
        if (prevVisibleButtonIndex == -1) {
            return null;
        }
        return getChildButton(prevVisibleButtonIndex);
    }

    private void updateChildOrder() {
        SortedMap<MaterialButton, Integer> viewToIndexMap = new TreeMap<>(this.childOrderComparator);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            viewToIndexMap.put(getChildButton(i), Integer.valueOf(i));
        }
        this.childOrder = (Integer[]) viewToIndexMap.values().toArray(new Integer[0]);
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        for (int i = 0; i < getChildCount(); i++) {
            MaterialButton childButton = getChildButton(i);
            childButton.setEnabled(enabled);
        }
    }

    private class PressedStateTracker implements MaterialButton.OnPressedChangeListener {
        private PressedStateTracker() {
        }

        @Override // com.google.android.material.button.MaterialButton.OnPressedChangeListener
        public void onPressedChanged(MaterialButton button, boolean isPressed) {
            MaterialButtonGroup.this.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
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
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.MaterialButtonGroup_Layout);
            this.overflowIcon = attributes.getDrawable(R.styleable.MaterialButtonGroup_Layout_layout_overflowIcon);
            this.overflowText = attributes.getText(R.styleable.MaterialButtonGroup_Layout_layout_overflowText);
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

    public static class OverflowUtils {
        private OverflowUtils() {
        }

        public static CharSequence getMenuItemText(View view, CharSequence text) {
            if (!TextUtils.isEmpty(text)) {
                return text;
            }
            if ((view instanceof MaterialButton) && !TextUtils.isEmpty(((MaterialButton) view).getText())) {
                return ((MaterialButton) view).getText();
            }
            return view.getContentDescription();
        }
    }
}
