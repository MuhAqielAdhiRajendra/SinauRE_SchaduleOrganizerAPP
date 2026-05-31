package com.google.android.material.focus;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.animation.OvershootInterpolator;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearance;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.StateListShapeAppearanceModel;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes13.dex */
public class FocusRingDrawable extends DrawableWrapper {
    private static final int ANIMATION_DURATION = 300;
    private static final boolean DEBUG_COLORS = false;
    private static final Drawable EMPTY_DRAWABLE = new ColorDrawable(0);
    private static final int[] FOCUSED_STATE_SET = {R.attr.state_focused, R.attr.state_window_focused};
    private static final TimeInterpolator INTERPOLATOR = new OvershootInterpolator(4.0f);
    private static final FloatProperty<FocusRingDrawable> PROPERTY_INTERPOLATION = new FloatProperty<FocusRingDrawable>("interpolation") { // from class: com.google.android.material.focus.FocusRingDrawable.1
        @Override // android.util.FloatProperty
        public void setValue(FocusRingDrawable drawable, float value) {
            drawable.interpolation = value;
            drawable.invalidateSelf();
        }

        @Override // android.util.Property
        public Float get(FocusRingDrawable drawable) {
            return Float.valueOf(drawable.interpolation);
        }
    };
    private ObjectAnimator animator;
    private boolean focused;
    private float interpolation;
    private WeakReference<MaterialShapeDrawable> materialShapeDrawable;
    private final Matrix matrix;
    private boolean mutated;
    private final Paint paint;
    private final ShapeAppearancePathProvider pathProvider;
    private boolean previousStateSetEmpty;
    private float shapeAppearanceCornerSize;
    private final Path shapeAppearancePath;
    private FocusRingState state;
    private final Path tmpPath;
    private final Rect tmpRect;
    private final RectF tmpRectF;

    public static Drawable wrap(Context context, Drawable drawable) {
        if (!shouldUseFocusRing(context)) {
            return drawable;
        }
        return new FocusRingDrawable(context, drawable);
    }

    public static FocusRingDrawable layer(Context context, LayerDrawable layerDrawable) {
        return layer(context, layerDrawable, null);
    }

    public static FocusRingDrawable layer(Context context, LayerDrawable layerDrawable, MaterialShapeDrawable materialShapeDrawable) {
        if (!shouldUseFocusRing(context)) {
            return null;
        }
        FocusRingDrawable focusRingDrawable = new FocusRingDrawable(context, EMPTY_DRAWABLE);
        if (materialShapeDrawable != null) {
            focusRingDrawable.setFocusRingMaterialShapeDrawable(materialShapeDrawable);
        }
        layerDrawable.addLayer(focusRingDrawable);
        focusRingDrawable.setCallback(layerDrawable);
        return focusRingDrawable;
    }

    private static boolean shouldUseFocusRing(Context context) {
        return MaterialAttributes.resolveBoolean(context.getTheme(), com.google.android.material.R.attr.focusRingsEnabled, false);
    }

    public static FocusRingDrawable findAndMutate(Drawable drawable) {
        if (drawable == null || find(drawable) == null) {
            return null;
        }
        drawable.mutate();
        return find(drawable);
    }

    public static FocusRingDrawable find(Drawable drawable) {
        if (drawable instanceof FocusRingDrawable) {
            return (FocusRingDrawable) drawable;
        }
        if (drawable instanceof DrawableWrapper) {
            Drawable inner = ((DrawableWrapper) drawable).getDrawable();
            if (inner instanceof FocusRingDrawable) {
                return (FocusRingDrawable) inner;
            }
        }
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            for (int i = 0; i < layerDrawable.getNumberOfLayers(); i++) {
                Drawable layer = layerDrawable.getDrawable(i);
                if (layer instanceof FocusRingDrawable) {
                    return (FocusRingDrawable) layer;
                }
            }
            return null;
        }
        return null;
    }

    public FocusRingDrawable() {
        super(null);
        this.paint = new Paint(1);
        this.tmpRectF = new RectF();
        this.tmpRect = new Rect();
        this.tmpPath = new Path();
        this.shapeAppearancePath = new Path();
        this.matrix = new Matrix();
        this.pathProvider = ShapeAppearancePathProvider.getInstanceOrCreate();
        this.shapeAppearanceCornerSize = -1.0f;
        this.interpolation = 1.0f;
        this.focused = false;
        this.mutated = false;
        this.state = new FocusRingState(null);
    }

    public FocusRingDrawable(Context context, Drawable drawable) {
        super(drawable);
        this.paint = new Paint(1);
        this.tmpRectF = new RectF();
        this.tmpRect = new Rect();
        this.tmpPath = new Path();
        this.shapeAppearancePath = new Path();
        this.matrix = new Matrix();
        this.pathProvider = ShapeAppearancePathProvider.getInstanceOrCreate();
        this.shapeAppearanceCornerSize = -1.0f;
        this.interpolation = 1.0f;
        this.focused = false;
        this.mutated = false;
        this.state = new FocusRingState(null);
        if (drawable != null) {
            this.state.wrappedState = drawable.getConstantState();
        }
        init(context.getTheme());
    }

    private FocusRingDrawable(FocusRingState state, Resources resources) {
        Drawable wrappedDrawable;
        super(null);
        this.paint = new Paint(1);
        this.tmpRectF = new RectF();
        this.tmpRect = new Rect();
        this.tmpPath = new Path();
        this.shapeAppearancePath = new Path();
        this.matrix = new Matrix();
        this.pathProvider = ShapeAppearancePathProvider.getInstanceOrCreate();
        this.shapeAppearanceCornerSize = -1.0f;
        this.interpolation = 1.0f;
        this.focused = false;
        this.mutated = false;
        this.state = new FocusRingState(state);
        if (this.state.wrappedState != null) {
            FocusRingState focusRingState = this.state;
            if (resources != null) {
                wrappedDrawable = focusRingState.wrappedState.newDrawable(resources);
            } else {
                wrappedDrawable = focusRingState.wrappedState.newDrawable();
            }
            setDrawable(wrappedDrawable);
        }
        updateLocalState();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return true;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        init(theme);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources res, XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
        inflate(res, parser, attrs, null);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void inflate(Resources res, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray a;
        super.inflate(res, parser, attrs, theme);
        if (theme != null) {
            a = theme.obtainStyledAttributes(attrs, com.google.android.material.R.styleable.FocusRingDrawable, 0, 0);
        } else {
            a = res.obtainAttributes(attrs, com.google.android.material.R.styleable.FocusRingDrawable);
        }
        updateStateFromTypedArrayWithoutThemeAttrsOrDefaults(a);
        a.recycle();
        inflateChildDrawable(res, parser, attrs, theme);
    }

    private void updateStateFromTypedArrayWithoutThemeAttrsOrDefaults(TypedArray a) {
        this.state.ringEnabledAttr = getValueDataIfAttr(a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsEnabled);
        if (this.state.ringEnabledAttr == Integer.MIN_VALUE && a.hasValue(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsEnabled)) {
            this.state.ringEnabled = a.getBoolean(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsEnabled, this.state.ringEnabled);
            this.state.ringEnabledInflated = true;
        }
        this.state.ringOuterColorAttr = getValueDataIfAttr(a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsOuterStrokeColor);
        if (this.state.ringOuterColorAttr == Integer.MIN_VALUE) {
            this.state.ringOuterColor = a.getColor(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsOuterStrokeColor, Integer.MIN_VALUE);
        }
        this.state.ringInnerColorAttr = getValueDataIfAttr(a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeColor);
        if (this.state.ringInnerColorAttr == Integer.MIN_VALUE) {
            this.state.ringInnerColor = a.getColor(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeColor, Integer.MIN_VALUE);
        }
        this.state.ringOuterStrokeWidthAttr = getValueDataIfAttr(a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsOuterStrokeWidth);
        if (this.state.ringOuterStrokeWidthAttr == Integer.MIN_VALUE) {
            this.state.ringOuterStrokeWidth = a.getDimension(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsOuterStrokeWidth, Float.NaN);
        }
        this.state.ringInnerStrokeWidthAttr = getValueDataIfAttr(a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeWidth);
        if (this.state.ringInnerStrokeWidthAttr == Integer.MIN_VALUE) {
            this.state.ringInnerStrokeWidth = a.getDimension(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeWidth, Float.NaN);
        }
        this.state.ringInnerStrokeWidthAttr = getValueDataIfAttr(a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeWidth);
        if (this.state.ringInnerStrokeWidthAttr == Integer.MIN_VALUE) {
            this.state.ringInnerStrokeWidth = a.getDimension(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeWidth, Float.NaN);
        }
        this.state.ringRadiusAttr = getValueDataIfAttr(a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsRadius);
        if (this.state.ringRadiusAttr == Integer.MIN_VALUE) {
            this.state.ringRadius = a.getDimension(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsRadius, Float.NaN);
        }
        this.state.ringInsetAttr = getValueDataIfAttr(a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInset);
        if (this.state.ringInsetAttr == Integer.MIN_VALUE) {
            this.state.ringInset = a.getDimension(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInset, Float.NaN);
        }
        this.state.ringInnerInsetAttr = getValueDataIfAttr(a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeInset);
        if (this.state.ringInnerInsetAttr == Integer.MIN_VALUE) {
            this.state.ringInnerInset = a.getDimension(com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeInset, Float.NaN);
        }
        this.state.ringShapeAppearanceAttr = getValueDataIfAttr(a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsShapeAppearance);
        this.state.ringShapeAppearanceResId = getResIdIfReference(a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsShapeAppearance);
    }

    private void updateStateFromTypedArrayWithThemeAttrsAndDefaults(TypedArray a, Resources.Theme theme) {
        int shapeAppearanceAttr;
        TypedValue typedValue;
        if (this.state.ringEnabledAttr != Integer.MIN_VALUE && (typedValue = MaterialAttributes.resolve(theme, this.state.ringEnabledAttr)) != null) {
            this.state.ringEnabled = typedValue.data != 0;
            this.state.ringEnabledInflated = true;
        }
        if (!this.state.ringEnabledInflated) {
            this.state.ringEnabled = MaterialAttributes.resolveBoolean(theme, com.google.android.material.R.attr.focusRingsEnabled, this.state.ringEnabled);
        }
        if (!this.state.ringEnabled) {
            return;
        }
        this.state.ringOuterColor = maybeResolveColor(this.state.ringOuterColor, theme, this.state.ringOuterColorAttr, a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsOuterStrokeColor, -16777216);
        this.state.ringInnerColor = maybeResolveColor(this.state.ringInnerColor, theme, this.state.ringInnerColorAttr, a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeColor, -1);
        this.state.ringOuterStrokeWidth = maybeResolveDimension(this.state.ringOuterStrokeWidth, theme, this.state.ringOuterStrokeWidthAttr, a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsOuterStrokeWidth, com.google.android.material.R.dimen.mtrl_focus_ring_outer_stroke_width);
        this.state.ringInnerStrokeWidth = maybeResolveDimension(this.state.ringInnerStrokeWidth, theme, this.state.ringInnerStrokeWidthAttr, a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeWidth, com.google.android.material.R.dimen.mtrl_focus_ring_inner_stroke_width);
        this.state.ringRadius = maybeResolveDimension(this.state.ringRadius, theme, this.state.ringRadiusAttr, a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsRadius, 0);
        this.state.ringInset = maybeResolveDimension(this.state.ringInset, theme, this.state.ringInsetAttr, a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInset, 0);
        if (Float.isNaN(this.state.ringInset)) {
            this.state.ringInset = 0.0f;
        }
        this.state.ringInnerInset = maybeResolveDimension(this.state.ringInnerInset, theme, this.state.ringInnerInsetAttr, a, com.google.android.material.R.styleable.FocusRingDrawable_focusRingsInnerStrokeInset, com.google.android.material.R.dimen.mtrl_focus_ring_inner_stroke_inset);
        int i = this.state.ringShapeAppearanceResId;
        FocusRingState focusRingState = this.state;
        if (i != Integer.MIN_VALUE) {
            focusRingState.ringShapeAppearance = ShapeAppearanceModel.builder(theme, this.state.ringShapeAppearanceResId).build();
            return;
        }
        if (focusRingState.ringShapeAppearanceAttr == Integer.MIN_VALUE) {
            shapeAppearanceAttr = com.google.android.material.R.attr.focusRingsShapeAppearance;
        } else {
            shapeAppearanceAttr = this.state.ringShapeAppearanceAttr;
        }
        TypedValue typedValue2 = MaterialAttributes.resolve(theme, shapeAppearanceAttr);
        if (typedValue2 == null) {
            return;
        }
        this.state.ringShapeAppearance = ShapeAppearanceModel.builder(theme, typedValue2.resourceId).build();
    }

    private int getValueDataIfAttr(TypedArray a, int index) {
        if (a.getType(index) == 2) {
            TypedValue value = new TypedValue();
            if (a.getValue(index, value)) {
                return value.data;
            }
            return Integer.MIN_VALUE;
        }
        return Integer.MIN_VALUE;
    }

    private int getResIdIfReference(TypedArray a, int index) {
        if (a.getType(index) == 1) {
            return a.getResourceId(index, Integer.MIN_VALUE);
        }
        return Integer.MIN_VALUE;
    }

    private int maybeResolveColor(int currentValue, Resources.Theme theme, int attrIndex, TypedArray a, int regularIndex, int defaultValue) {
        if (currentValue != Integer.MIN_VALUE) {
            return currentValue;
        }
        if (attrIndex != Integer.MIN_VALUE) {
            TypedValue value = new TypedValue();
            if (theme.resolveAttribute(attrIndex, value, true)) {
                return value.data;
            }
        }
        return a.getColor(regularIndex, defaultValue);
    }

    private float maybeResolveDimension(float currentValue, Resources.Theme theme, int attrIndex, TypedArray a, int regularIndex, int defaultValueResId) {
        if (!Float.isNaN(currentValue)) {
            return currentValue;
        }
        Resources resources = theme.getResources();
        if (attrIndex != Float.MIN_VALUE) {
            TypedValue value = new TypedValue();
            if (theme.resolveAttribute(attrIndex, value, true)) {
                return value.getDimension(resources.getDisplayMetrics());
            }
        }
        float value2 = a.getDimension(regularIndex, Float.NaN);
        if (!Float.isNaN(value2)) {
            return value2;
        }
        if (defaultValueResId == 0) {
            return Float.NaN;
        }
        return resources.getDimension(defaultValueResId);
    }

    private void inflateChildDrawable(Resources res, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = null;
        int outerDepth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if (type == 1 || (type == 3 && parser.getDepth() <= outerDepth)) {
                break;
            } else if (type == 2) {
                drawable = Drawable.createFromXmlInner(res, parser, attrs, theme);
            }
        }
        if (drawable != null) {
            setDrawable(drawable);
            this.state.wrappedState = drawable.getConstantState();
        } else {
            setDrawable(EMPTY_DRAWABLE);
            this.state.wrappedState = EMPTY_DRAWABLE.getConstantState();
        }
    }

    private void init(Resources.Theme theme) {
        TypedArray a = theme.obtainStyledAttributes(com.google.android.material.R.styleable.FocusRingDrawable);
        updateStateFromTypedArrayWithThemeAttrsAndDefaults(a, theme);
        a.recycle();
        updateLocalState();
    }

    private void updateLocalState() {
        this.paint.setStyle(Paint.Style.STROKE);
        if (Float.isNaN(this.state.ringOuterStrokeWidth)) {
            return;
        }
        this.paint.setStrokeWidth(this.state.ringOuterStrokeWidth);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        if (!this.state.ringEnabled) {
            return;
        }
        calculateShapeAppearanceRoundRectOrPath();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isProjected() {
        Drawable drawable = getDrawable();
        return drawable != null && drawable.isProjected();
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] stateSet) {
        if (!this.state.ringEnabled) {
            this.focused = false;
            return super.onStateChange(stateSet);
        }
        boolean focused = StateSet.stateSetMatches(this.state.ringStateSet, stateSet);
        boolean changed = this.focused != focused;
        this.focused = focused;
        if (changed && stateSet.length > 0 && !this.previousStateSetEmpty) {
            maybeAnimate(focused);
        }
        this.previousStateSetEmpty = stateSet.length == 0;
        return super.onStateChange(stateSet) || changed;
    }

    private void maybeAnimate(boolean focused) {
        if (this.animator != null) {
            this.animator.cancel();
            this.animator = null;
        }
        if (focused) {
            this.animator = createAnimator();
            this.animator.start();
        } else {
            this.interpolation = 1.0f;
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        if (this.animator != null) {
            this.animator.end();
            this.animator = null;
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return super.isStateful() || this.state.ringEnabled;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public boolean hasFocusStateSpecified() {
        try {
            if (!super.hasFocusStateSpecified()) {
                if (!this.state.ringEnabled) {
                    return false;
                }
            }
            return true;
        } catch (NoSuchMethodError e) {
            return this.state.ringEnabled;
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.state.ringEnabled && this.focused) {
            float outerInset = calculateOuterInset();
            float innerInset = calculateInnerInset();
            Path path = getNonEmptyPath();
            if (path == null) {
                float outerRadius = calculateOuterRadius();
                float innerRadius = calculateInnerRadius(outerRadius);
                drawRoundRect(canvas, innerRadius, innerInset, this.state.ringInnerStrokeWidth, this.state.ringInnerColor);
                drawRoundRect(canvas, outerRadius, outerInset, this.state.ringOuterStrokeWidth, this.state.ringOuterColor);
                return;
            }
            drawPath(canvas, path, innerInset, this.state.ringInnerStrokeWidth, this.state.ringInnerColor);
            drawPath(canvas, path, outerInset, this.state.ringOuterStrokeWidth, this.state.ringOuterColor);
        }
    }

    private Path getNonEmptyPath() {
        if (!this.shapeAppearancePath.isEmpty()) {
            return this.shapeAppearancePath;
        }
        if (this.materialShapeDrawable != null && this.materialShapeDrawable.get() != null) {
            Path path = this.materialShapeDrawable.get().getPath();
            if (!path.isEmpty()) {
                return path;
            }
            return null;
        }
        return null;
    }

    private void drawPath(Canvas canvas, Path path, float inset, float strokeWidth, int color) {
        calculateBounds(this.tmpRectF);
        float scaleX = 1.0f - ((inset * 2.0f) / this.tmpRectF.width());
        float scaleY = 1.0f - ((2.0f * inset) / this.tmpRectF.height());
        this.matrix.reset();
        this.matrix.postScale(scaleX, scaleY, this.tmpRectF.centerX(), this.tmpRectF.centerY());
        path.transform(this.matrix, this.tmpPath);
        this.paint.setStrokeWidth(this.interpolation * strokeWidth);
        this.paint.setColor(color);
        canvas.drawPath(this.tmpPath, this.paint);
    }

    private void drawRoundRect(Canvas canvas, float radius, float inset, float strokeWidth, int color) {
        calculateBounds(this.tmpRectF);
        this.tmpRectF.inset(inset, inset);
        this.paint.setStrokeWidth(this.interpolation * strokeWidth);
        this.paint.setColor(color);
        canvas.drawRoundRect(this.tmpRectF, radius, radius, this.paint);
    }

    public boolean isFocusRingEnabled() {
        return this.state.ringEnabled;
    }

    public void setFocusRingEnabled(boolean enabled) {
        this.state.ringEnabled = enabled;
    }

    public int getFocusRingOuterStrokeColor() {
        return this.state.ringOuterColor;
    }

    public void setFocusRingOuterStrokeColor(int outerStrokeColor) {
        this.state.ringOuterColor = outerStrokeColor;
    }

    public int getFocusRingInnerStrokeColor() {
        return this.state.ringInnerColor;
    }

    public void setFocusRingInnerStrokeColor(int innerStrokeColor) {
        this.state.ringInnerColor = innerStrokeColor;
    }

    public float getFocusRingOuterStrokeWidth() {
        return this.state.ringOuterStrokeWidth;
    }

    public void setFocusRingOuterStrokeWidth(float outerStrokeWidth) {
        this.state.ringOuterStrokeWidth = outerStrokeWidth;
    }

    public float getFocusRingInnerStrokeWidth() {
        return this.state.ringInnerStrokeWidth;
    }

    public void setFocusRingInnerStrokeWidth(float innerStrokeWidth) {
        this.state.ringInnerStrokeWidth = innerStrokeWidth;
    }

    public float getFocusRingInset() {
        return this.state.ringInset;
    }

    public void setFocusRingInset(float inset) {
        this.state.ringInset = inset;
    }

    public float getFocusRingInnerInset() {
        return this.state.ringInnerInset;
    }

    public void setFocusRingInnerInset(float innerInset) {
        this.state.ringInnerInset = innerInset;
    }

    public float getFocusRingRadius() {
        return this.state.ringRadius;
    }

    public void setFocusRingRadius(float radius) {
        this.state.ringRadius = radius;
    }

    public MaterialShapeDrawable getFocusRingMaterialShapeDrawable() {
        if (this.materialShapeDrawable != null) {
            return this.materialShapeDrawable.get();
        }
        return null;
    }

    public void setFocusRingMaterialShapeDrawable(MaterialShapeDrawable materialShapeDrawable) {
        this.materialShapeDrawable = new WeakReference<>(materialShapeDrawable);
    }

    public ShapeAppearance getFocusRingShapeAppearance() {
        return this.state.ringShapeAppearance;
    }

    public void setFocusRingShapeAppearance(ShapeAppearance shapeAppearance) {
        this.state.ringShapeAppearance = shapeAppearance;
    }

    public boolean updateFocusRingShapeAppearanceFromWrappedDrawable() {
        ShapeAppearance shapeAppearance = toShapeAppearance(getDrawable());
        if (shapeAppearance != null) {
            updateShapeAppearanceCornerSizeOrPath(shapeAppearance);
            return true;
        }
        return false;
    }

    public Rect getFocusRingBounds() {
        return this.state.ringCustomBounds;
    }

    public void setFocusRingBounds(Rect bounds) {
        this.state.ringCustomBounds = bounds;
    }

    public void setFocusRingBounds(int left, int top, int right, int bottom) {
        if (this.state.ringCustomBounds == null) {
            this.state.ringCustomBounds = new Rect();
        }
        this.state.ringCustomBounds.set(left, top, right, bottom);
    }

    public int[] getFocusRingStateSet() {
        return this.state.ringStateSet;
    }

    public void setFocusRingStateSet(int[] stateSet) {
        this.state.ringStateSet = stateSet;
    }

    private void calculateBounds(RectF rectF) {
        if (this.state.ringCustomBounds == null) {
            if (this.materialShapeDrawable != null && this.materialShapeDrawable.get() != null) {
                rectF.set(this.materialShapeDrawable.get().getBounds());
                return;
            }
            if (getDrawable() instanceof RippleDrawable) {
                RippleDrawable rippleDrawable = (RippleDrawable) getDrawable();
                rippleDrawable.getHotspotBounds(this.tmpRect);
                int radius = rippleDrawable.getRadius();
                if (radius > 0) {
                    int insetHorizontal = Math.max(0, (this.tmpRect.width() / 2) - radius);
                    int insetVertical = Math.max(0, (this.tmpRect.height() / 2) - radius);
                    this.tmpRect.inset(insetHorizontal, insetVertical);
                }
                rectF.set(this.tmpRect);
                return;
            }
            rectF.set(getBounds());
            return;
        }
        rectF.set(this.state.ringCustomBounds);
    }

    private float calculateOuterInset() {
        return this.state.ringInset + ((this.state.ringOuterStrokeWidth / 2.0f) * this.interpolation);
    }

    private float calculateInnerInset() {
        return this.state.ringInset + this.state.ringInnerInset + ((this.state.ringInnerStrokeWidth / 2.0f) * this.interpolation);
    }

    private float calculateOuterRadius() {
        int radius;
        if (!Float.isNaN(this.state.ringRadius)) {
            return this.state.ringRadius;
        }
        if (this.shapeAppearanceCornerSize >= 0.0f) {
            return this.shapeAppearanceCornerSize;
        }
        if (this.materialShapeDrawable != null && this.materialShapeDrawable.get() != null) {
            float roundRectCornerSize = this.materialShapeDrawable.get().calculateRoundRectCornerSize();
            if (roundRectCornerSize >= 0.0f) {
                return Math.max(0.0f, roundRectCornerSize - (this.state.ringOuterStrokeWidth / 2.0f));
            }
        }
        Drawable drawable = getDrawable();
        if (!(drawable instanceof RippleDrawable) || (radius = ((RippleDrawable) drawable).getRadius()) < 0) {
            return 0.0f;
        }
        return radius;
    }

    private float calculateInnerRadius(float outerRadius) {
        return Math.max(0.0f, outerRadius - (this.state.ringOuterStrokeWidth / 2.0f));
    }

    private void calculateShapeAppearanceRoundRectOrPath() {
        if (this.state.ringShapeAppearance == null) {
            if (updateFocusRingShapeAppearanceFromWrappedDrawable()) {
                return;
            }
            this.shapeAppearanceCornerSize = -1.0f;
            this.shapeAppearancePath.reset();
            return;
        }
        updateShapeAppearanceCornerSizeOrPath(this.state.ringShapeAppearance);
    }

    private void updateShapeAppearanceCornerSizeOrPath(ShapeAppearance shapeAppearance) {
        calculateBounds(this.tmpRectF);
        ShapeAppearanceModel shapeAppearanceModel = shapeAppearance.getShapeForState(FOCUSED_STATE_SET);
        if (shapeAppearanceModel.isRoundRect(this.tmpRectF)) {
            float outerInset = calculateOuterInset();
            this.tmpRectF.inset(outerInset, outerInset);
            this.shapeAppearanceCornerSize = shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(this.tmpRectF);
            this.shapeAppearancePath.reset();
            return;
        }
        this.pathProvider.calculatePath(shapeAppearanceModel, null, 1.0f, this.tmpRectF, null, this.shapeAppearancePath);
        this.shapeAppearanceCornerSize = -1.0f;
    }

    private ShapeAppearance toShapeAppearance(Drawable drawable) {
        if (drawable instanceof ShapeDrawable) {
            return toShapeAppearance((ShapeDrawable) drawable);
        }
        if (drawable instanceof GradientDrawable) {
            return toShapeAppearance((GradientDrawable) drawable);
        }
        return null;
    }

    private ShapeAppearance toShapeAppearance(ShapeDrawable shapeDrawable) {
        Outline outline = new Outline();
        shapeDrawable.getOutline(outline);
        if (outline.getRadius() > 0.0f) {
            return ShapeAppearanceModel.builder().setAllCornerSizes(outline.getRadius()).build();
        }
        return null;
    }

    private ShapeAppearance toShapeAppearance(GradientDrawable gradientDrawable) {
        float[] cornerRadii = getCornerRadiiOrNull(gradientDrawable);
        if (cornerRadii != null) {
            return ShapeAppearanceModel.builder().setTopLeftCornerSize(Math.min(cornerRadii[0], cornerRadii[1])).setTopRightCornerSize(Math.min(cornerRadii[2], cornerRadii[3])).setBottomRightCornerSize(Math.min(cornerRadii[4], cornerRadii[5])).setBottomLeftCornerSize(Math.min(cornerRadii[6], cornerRadii[7])).build();
        }
        float cornerRadius = getCornerRadius(gradientDrawable);
        if (cornerRadius > 0.0f) {
            return ShapeAppearanceModel.builder().setAllCornerSizes(cornerRadius).build();
        }
        return null;
    }

    private float[] getCornerRadiiOrNull(GradientDrawable gradientDrawable) {
        try {
            return gradientDrawable.getCornerRadii();
        } catch (NullPointerException e) {
            return null;
        }
    }

    private float getCornerRadius(GradientDrawable gradientDrawable) {
        try {
            return gradientDrawable.getCornerRadius();
        } catch (NullPointerException e) {
            return -1.0f;
        }
    }

    private ObjectAnimator createAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, PROPERTY_INTERPOLATION, 0.0f, 1.0f);
        animator.setDuration(300L);
        animator.setInterpolator(INTERPOLATOR);
        animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.focus.FocusRingDrawable.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                FocusRingDrawable.this.interpolation = 1.0f;
                FocusRingDrawable.this.invalidateSelf();
            }
        });
        return animator;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.mutated && super.mutate() == this) {
            this.state = new FocusRingState(this.state);
            Drawable drawable = getDrawable();
            if (drawable != null) {
                this.state.wrappedState = drawable.getConstantState();
            }
            this.mutated = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.state.canConstantState()) {
            this.state.mChangingConfigurations = getChangingConfigurations();
            return this.state;
        }
        return null;
    }

    private static final class FocusRingState extends Drawable.ConstantState {
        int mChangingConfigurations;
        private Rect ringCustomBounds;
        private boolean ringEnabled;
        private int ringEnabledAttr;
        private boolean ringEnabledInflated;
        private int ringInnerColor;
        private int ringInnerColorAttr;
        private float ringInnerInset;
        private int ringInnerInsetAttr;
        private float ringInnerStrokeWidth;
        private int ringInnerStrokeWidthAttr;
        private float ringInset;
        private int ringInsetAttr;
        private int ringOuterColor;
        private int ringOuterColorAttr;
        private float ringOuterStrokeWidth;
        private int ringOuterStrokeWidthAttr;
        private float ringRadius;
        private int ringRadiusAttr;
        private ShapeAppearance ringShapeAppearance;
        private int ringShapeAppearanceAttr;
        private int ringShapeAppearanceResId;
        private int[] ringStateSet;
        Drawable.ConstantState wrappedState;

        FocusRingState(FocusRingState orig) {
            this.mChangingConfigurations = 0;
            this.ringEnabled = false;
            this.ringEnabledAttr = Integer.MIN_VALUE;
            this.ringEnabledInflated = false;
            this.ringOuterColor = Integer.MIN_VALUE;
            this.ringOuterColorAttr = Integer.MIN_VALUE;
            this.ringInnerColor = Integer.MIN_VALUE;
            this.ringInnerColorAttr = Integer.MIN_VALUE;
            this.ringOuterStrokeWidth = Float.NaN;
            this.ringOuterStrokeWidthAttr = Integer.MIN_VALUE;
            this.ringInnerStrokeWidth = Float.NaN;
            this.ringInnerStrokeWidthAttr = Integer.MIN_VALUE;
            this.ringRadius = Float.NaN;
            this.ringRadiusAttr = Integer.MIN_VALUE;
            this.ringInset = Float.NaN;
            this.ringInsetAttr = Integer.MIN_VALUE;
            this.ringInnerInset = Float.NaN;
            this.ringInnerInsetAttr = Integer.MIN_VALUE;
            this.ringShapeAppearance = null;
            this.ringShapeAppearanceResId = Integer.MIN_VALUE;
            this.ringShapeAppearanceAttr = Integer.MIN_VALUE;
            this.ringCustomBounds = null;
            this.ringStateSet = FocusRingDrawable.FOCUSED_STATE_SET;
            if (orig != null) {
                this.wrappedState = orig.wrappedState;
                this.mChangingConfigurations = orig.mChangingConfigurations;
                this.ringEnabled = orig.ringEnabled;
                this.ringEnabledAttr = orig.ringEnabledAttr;
                this.ringEnabledInflated = orig.ringEnabledInflated;
                this.ringOuterColor = orig.ringOuterColor;
                this.ringOuterColorAttr = orig.ringOuterColorAttr;
                this.ringInnerColor = orig.ringInnerColor;
                this.ringInnerColorAttr = orig.ringInnerColorAttr;
                this.ringOuterStrokeWidth = orig.ringOuterStrokeWidth;
                this.ringOuterStrokeWidthAttr = orig.ringOuterStrokeWidthAttr;
                this.ringInnerStrokeWidth = orig.ringInnerStrokeWidth;
                this.ringInnerStrokeWidthAttr = orig.ringInnerStrokeWidthAttr;
                this.ringRadius = orig.ringRadius;
                this.ringRadiusAttr = orig.ringRadiusAttr;
                this.ringInset = orig.ringInset;
                this.ringInsetAttr = orig.ringInsetAttr;
                this.ringInnerInset = orig.ringInnerInset;
                this.ringInnerInsetAttr = orig.ringInnerInsetAttr;
                this.ringShapeAppearanceResId = orig.ringShapeAppearanceResId;
                this.ringShapeAppearanceAttr = orig.ringShapeAppearanceAttr;
                if (orig.ringShapeAppearance instanceof ShapeAppearanceModel) {
                    this.ringShapeAppearance = ((ShapeAppearanceModel) orig.ringShapeAppearance).toBuilder().build();
                } else if (orig.ringShapeAppearance instanceof StateListShapeAppearanceModel) {
                    this.ringShapeAppearance = ((StateListShapeAppearanceModel) orig.ringShapeAppearance).toBuilder().build();
                } else {
                    this.ringShapeAppearance = orig.ringShapeAppearance;
                }
                if (orig.ringCustomBounds != null) {
                    this.ringCustomBounds = new Rect(orig.ringCustomBounds);
                }
                this.ringStateSet = Arrays.copyOf(orig.ringStateSet, orig.ringStateSet.length);
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new FocusRingDrawable(this, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources res) {
            return new FocusRingDrawable(this, res);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            int wrappedChangingConfigs = this.wrappedState != null ? this.wrappedState.getChangingConfigurations() : 0;
            return this.mChangingConfigurations | wrappedChangingConfigs;
        }

        boolean canConstantState() {
            return this.wrappedState != null;
        }
    }
}
